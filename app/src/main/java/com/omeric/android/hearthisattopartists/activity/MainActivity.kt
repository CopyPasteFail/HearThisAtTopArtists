package com.omeric.android.hearthisattopartists.activity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.omeric.android.hearthisattopartists.data.remote.HearThisAtApiService
import io.reactivex.disposables.Disposable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.omeric.android.hearthisattopartists.R
import com.omeric.android.hearthisattopartists.adapter.MoviesAdapter
import java.util.*
import com.omeric.android.hearthisattopartists.adapter.EndlessRecyclerViewScrollListener
import com.omeric.android.hearthisattopartists.data.model.TrackModel
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity(), MediaPlayer.OnErrorListener
{
    companion object
    {
        const val BASE_URL_API = "https://api-v2.hearthis.at/"
        private const val SKIP_TIME_DURATION_MS = 5000
        private const val GRAYED_OUT = .5f
        private const val UNGRAYED_OUT = 1.0f
    }

    enum class MediaPlayerState
    {
        IDLE, INITIALIZED, PREPARING, PREPARED, STARTED, PAUSED, COMPLETED, ERROR, END
    }

    //init tracks list
    var tracks = arrayListOf<TrackModel>()

    /**
     * [CompositeDisposable] is a convenient class for bundling up multiple Disposables,
     * so that you can dispose them all with one method call on [CompositeDisposable.dispose].
     */
    private var compositeDisposable: CompositeDisposable? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var dataSourceUrl : String = ""

    // Media player controls
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imageButtonPlay: ImageButton
    private lateinit var imageButtonPause: ImageButton
    private lateinit var imageButtonRewind : ImageButton
    private lateinit var imageButtonForwards : ImageButton

    private lateinit var textViewPass: TextView
    private lateinit var textViewDuration: TextView
    private lateinit var textViewDue: TextView
    private lateinit var mediaPlayerSeekBar: SeekBar
    private lateinit var imageVieArtwork : ImageView
    private lateinit var textViewTitle: TextView

    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var mediaPlayerState: MediaPlayerState = MediaPlayerState.IDLE

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_main_activity)

        /**
         * RecyclerView can perform several optimizations if it can know in advance that changes in adapter
         * content cannot change the size (dimensions) of the RecyclerView itself
         */
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        progressBar = findViewById(R.id.progressbar_main_activity)
        loadNextDataFromApi(1) // Load first page

        imageButtonRewind = findViewById(R.id.imgBtn_media_rewind)
        imageButtonPlay = findViewById(R.id.imgBtn_media_play)
        imageButtonPause = findViewById(R.id.imgBtn_media_pause)
        imageButtonForwards = findViewById(R.id.imgBtn_media_forwards)
        mediaPlayerSeekBar = findViewById(R.id.seek_bar)
        imageVieArtwork = findViewById(R.id.imgVw_artwork)
        textViewPass = findViewById(R.id.textVw_passed)
        textViewDuration = findViewById(R.id.textVw_duration)
        textViewDue = findViewById(R.id.textVw_remaining)
        textViewTitle = findViewById(R.id.textVw_title)

        initMediaPlayer()
        initMediaPlayerListeners()
        togglePlayUnclickable() // Playing starts when the user clicks on an artist

        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager)
        {
            override fun onLoadMore(page: Int, recyclerView: RecyclerView)
            {
                // New data needs to be appended to the list
                loadNextDataFromApi(page)
            }
        })
    }

    override fun onDestroy()
    {
        /** dispose all [Disposable]s */
        if (compositeDisposable?.isDisposed == false)
        {
            /*
             * The subscription can be disposed with a simple method call,
             * thus preventing those nasty situations when, for example,
             * rotating the device in the middle of a running background task causes a memory leak
             */
            compositeDisposable?.dispose()
            compositeDisposable = null
        }
        super.onDestroy()
    }

    /**
     * Create a CompositeDisposable object which acts as a container for disposables
     * (think Recycle Bin) and add our Disposable to it
     *
     * @param disposable the newly [Disposable] to add
     */
    fun add(disposable: Disposable)
    {
        if (compositeDisposable == null)
        {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    /**
     * Hiding the progress bar by inverting the visibility of [progressBar] and [recyclerView]
     */
    private fun hideProgressBar()
    {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    /**
     * Showing the progress bar by inverting the visibility of [progressBar] and [recyclerView]
     */
    private fun showProgressBar()
    {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    /**
     * This method sends out a network request and appends new data items to the adapter, by:
     * 1. Sending an API request including an offset value (i.e `page`) as a query parameter.
     * 2. Deserializing and constructing new model objects from the API response
     * 3. Appending the new data objects to the existing set of items inside the array of items
     * 4. Notifying the adapter of the new items made with `notifyDataSetChanged()`
     *
     * @param page The page number to be loaded
     */
    fun loadNextDataFromApi(page: Int)
    {
        // Trailing slash is needed
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        // create an instance of the HearThisAtApiService
        val hearThisAtApiService = retrofit.create(HearThisAtApiService::class.java)

        hearThisAtApiService.getPopularTracks("popular", page, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<TrackModel> /*PopularTracksModel*/>
            {
                override fun onSubscribe(disposable: Disposable)
                {
                    add(disposable)
                    showProgressBar()
                }

                // data is ready and we can update the UI
                override fun onSuccess(trackModelList : ArrayList<TrackModel>)
                {
                    // for the first page we connect the adapter and the tracks list
                    if (page == 1)
                    {
                        tracks = trackModelList

                        /** Hooking up [MoviesAdapter] and [recyclerView] */
                        recyclerView.adapter = (object : MoviesAdapter(tracks, R.layout.list_item_movie)
                        {
                            override fun playSongOfArtist(trackArtworkUrl : String, trackTitle : String, trackDataSourceUrl : String)
                            {
                                stopMediaPlayer()
                                dataSourceUrl = trackDataSourceUrl
                                textViewTitle.text = trackTitle
                                if (trackArtworkUrl.isNotEmpty())
                                {
                                    Picasso
                                        .get()
                                        .load(trackArtworkUrl)
                                        .placeholder(android.R.drawable.stat_sys_download) //loading image
                                        .error(android.R.drawable.stat_notify_error)
                                        .into(imageVieArtwork)
                                }
                                startPlaying()
                            }
                        })
                        hideProgressBar()
                    }
                    else
                    {
                        // for any further pages, we append the data and notify the adapter on the change
                        tracks.addAll(trackModelList)
                        recyclerView.adapter!!.notifyDataSetChanged()

                        hideProgressBar()
                    }
                }

                override fun onError(e: Throwable)
                {
                    // oops, we best show some error message
                    Toast.makeText(this@MainActivity, "Error connecting to HearThisAt", Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
            })
    }

    private fun initMediaPlayer()
    {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
                    AudioAttributes.CONTENT_TYPE_MUSIC
                ).build()
            )

            /** Called when [MediaPlayer] is ready */
            setOnPreparedListener {
                mediaPlayerState = MediaPlayerState.PREPARED

                startMediaPlayer()
            }

            // Called when the playback reaches the end of stream
            setOnCompletionListener {
                /** the [MediaPlayer] is now in the [MediaPlayerState.COMPLETED] state */
                stopMediaPlayer()
            }
        }
    }

    private fun initMediaPlayerListeners()
    {
        // Start the media player
        imageButtonPlay.setOnClickListener {
            startPlaying()
        }

        imageButtonPause.setOnClickListener {
            if (mediaPlayer.isPlaying)
            {
                mediaPlayer.pause()
                mediaPlayerState = MediaPlayerState.PAUSED
            }
            togglePlayVisible()
        }

        imageButtonForwards.setOnClickListener {
            val skipToTime = mediaPlayer.currentPosition + SKIP_TIME_DURATION_MS

            if (skipToTime <= mediaPlayer.duration)
            {
                mediaPlayer.seekTo(skipToTime)
            }
            else
            {
                mediaPlayer.seekTo(mediaPlayer.duration)
            }
        }

        imageButtonRewind.setOnClickListener {
            val skipToTime = mediaPlayer.currentPosition - SKIP_TIME_DURATION_MS

            if (skipToTime > 0)
            {
                mediaPlayer.seekTo(skipToTime)
            }
            else
            {
                mediaPlayer.seekTo(0)
            }
        }

        // Seek bar change listener
        mediaPlayerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean)
            {
                if (fromUser)
                {
                    mediaPlayer.seekTo(progress * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar)
            {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar)
            {
            }
        })
    }

    override fun onRestart()
    {
        super.onRestart()

        // re-init the media player
        initMediaPlayer()
    }

    override fun onPause()
    {
        super.onPause()
        releaseMediaPlayer()
    }

    // When the user switched apps or pressed the home button
    override fun onStop()
    {
        super.onStop()
        releaseMediaPlayer()
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean
    {
        // ... react appropriately ...
        /** The [MediaPlayer] has moved to the [MediaPlayerState.ERROR] state, must be reset! */
        mediaPlayerState = MediaPlayerState.ERROR
        Toast.makeText(this, applicationContext.getString(R.string.error_mediaplayer), Toast.LENGTH_SHORT).show()
        return false
    }

    private fun startMediaPlayer()
    {
        mediaPlayer.start()
        mediaPlayerState = MediaPlayerState.STARTED

        textViewDuration.text = convertToMinAndSec(mediaPlayer.durationSeconds)
        initializeSeekBar()

        togglePauseVisible()
    }

    // Stop the media player
    private fun stopMediaPlayer()
    {
        removeHandlerCallback()

        /** transfers a [MediaPlayer] object to the [MediaPlayerState.IDLE] state */
        mediaPlayer.reset()
        mediaPlayerState = MediaPlayerState.IDLE

        togglePlayVisible()
    }

    private fun releaseMediaPlayer()
    {
        removeHandlerCallback()

        /** transfers a [MediaPlayer] object to the [MediaPlayerState.END] state */
        mediaPlayer.release()
        mediaPlayerState = MediaPlayerState.END
        togglePlayVisible()
    }

    private fun togglePauseVisible()
    {
        imageButtonPlay.visibility = View.GONE
        imageButtonPause.visibility = View.VISIBLE
    }

    private fun togglePlayVisible()
    {
        imageButtonPlay.visibility = View.VISIBLE
        imageButtonPause.visibility = View.GONE
        togglePlayClickable()
    }

    private fun togglePlayUnclickable()
    {
        imageButtonPlay.isClickable = false
        imageButtonPlay.alpha = GRAYED_OUT
    }

    private fun togglePlayClickable()
    {
        imageButtonPlay.isClickable = true
        imageButtonPlay.alpha = UNGRAYED_OUT
    }

    private fun removeHandlerCallback()
    {
        if (::runnable.isInitialized)
        {
            handler.removeCallbacks(runnable)
        }
    }

    // Method to initialize seek bar and audio stats
    private fun initializeSeekBar()
    {
        mediaPlayerSeekBar.max = mediaPlayer.durationSeconds

        runnable = Runnable{
            mediaPlayerSeekBar.progress = mediaPlayer.currentSeconds

            textViewPass.text = convertToMinAndSec(mediaPlayer.currentSeconds)
            val diff = mediaPlayer.durationSeconds - mediaPlayer.currentSeconds
            textViewDue.text = convertToMinAndSec(diff)

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    // Extension property to get media player duration in seconds
    private val MediaPlayer.durationSeconds:Int
        get() {
            return this.duration/1000
        }

    // Extension property to get media player current position in seconds
    private val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

    // Extension property to convert seconds to compound duration - mm:ss
    private fun convertToMinAndSec(timeSeconds: Int): String
    {
        return "${timeSeconds/60}:"+"%02d".format(timeSeconds % 60)
    }

    private fun startPlaying()
    {
        if (dataSourceUrl.isEmpty()) return
        if (mediaPlayerState == MediaPlayerState.PREPARED || mediaPlayerState == MediaPlayerState.PAUSED)
        {
            startMediaPlayer()
        }
        else
        {
            togglePlayUnclickable() // Preventing the user from re-clicking
            mediaPlayer.setDataSource(dataSourceUrl)
            /**
             * transfers a [MediaPlayer] object in the [MediaPlayerState.IDLE] state to the
             * [MediaPlayerState.INITIALIZED] state
             * */
            mediaPlayerState = MediaPlayerState.INITIALIZED
            mediaPlayer.prepareAsync()
            mediaPlayerState = MediaPlayerState.PREPARING
        }
    }
}
