package com.omeric.android.hearthisattopartists.activity

//TODO - add auto-complete search bar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.omeric.android.hearthisattopartists.data.remote.HearThisAtApiService
import io.reactivex.disposables.Disposable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.omeric.android.hearthisattopartists.R
import com.omeric.android.hearthisattopartists.adapter.MoviesAdapter
import java.util.*
import com.omeric.android.hearthisattopartists.adapter.EndlessRecyclerViewScrollListener
import com.omeric.android.hearthisattopartists.data.model.TrackModel


class MainActivity : AppCompatActivity()
{
    companion object
    {
        private val TAG = "gipsy:" + this::class.java.name
        const val BASE_URL_API = "https://api-v2.hearthis.at/"
//        const val BASE_URL_MOVIE_POSTER = "https://image.tmdb.org/t/p/w185"
        const val BASE_URL_MOVIE_POSTER = "" //TODO - remove this
    }

    //init tracks list
    var tracks = arrayListOf<TrackModel>()
//    var pageNumber : Int = 1

    /**
     * [CompositeDisposable] is a convenient class for bundling up multiple Disposables,
     * so that you can dispose them all with one method call on [CompositeDisposable.dispose].
     */
    private var compositeDisposable: CompositeDisposable? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var totalPages = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d(TAG, ":onCreate")
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

        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager)
        {
            override fun onLoadMore(page: Int, recyclerView: RecyclerView)
            {
                Log.d(TAG, ":onCreate::recyclerView.addOnScrollListener::onLoadMore: page = $page")
                // New data needs to be appended to the list
                loadNextDataFromApi(page)
            }
        })
    }

    override fun onDestroy()
    {
        Log.d(TAG, ":onDestroy")
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
        Log.d(TAG, ":add")
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
        Log.d(TAG, "::hideProgressBar:")
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    /**
     * Showing the progress bar by inverting the visibility of [progressBar] and [recyclerView]
     */
    private fun showProgressBar()
    {
        Log.d(TAG, "::hideProgressBar:")
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
        //example: https://api-v2.hearthis.at/feed/?type=popular&page=1&count=20
        hearThisAtApiService.getPopularTracks("popular", page, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<TrackModel> /*PopularTracksModel*/>
            {
                override fun onSubscribe(disposable: Disposable)
                {
                    Log.d(TAG, " hearThisAtApiService.getPopularTracks::onSubscribe")
                    add(disposable)
                    showProgressBar()
                }

                // data is ready and we can update the UI
                override fun onSuccess(trackModelList : ArrayList<TrackModel> /*PopularTracksModel*/)
                {
                    // for the first page we connect the adapter and the tracks list
                    if (page == 1)
                    {
                        Log.d(TAG, "hearThisAtApiService.getPopularTracks::onSuccess: page #$page loaded successfully")
//                        totalPages = trackModelList.totalPages!!

                        tracks = trackModelList

                        /** Hooking up [MoviesAdapter] and [recyclerView] */
                        recyclerView.adapter = MoviesAdapter(tracks, R.layout.list_item_movie)
                        hideProgressBar()
                    }
                    else
                    {
                        // for any further pages, we append the data and notify the adapter on the change
                        Log.d(TAG, "hearThisAtApiService.getPopularTracks::onSuccess: page #$page loaded successfully")
                        tracks.addAll(trackModelList)
                        recyclerView.adapter!!.notifyDataSetChanged()

                        hideProgressBar()
                    }
                }

                override fun onError(e: Throwable)
                {
                    // oops, we best show some error message
                    Log.e(TAG, " hearThisAtApiService.getPopularTracks::onError: $e")
                    Toast.makeText(this@MainActivity, "Error connecting to HearThisAt", Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
            })
    }
}
