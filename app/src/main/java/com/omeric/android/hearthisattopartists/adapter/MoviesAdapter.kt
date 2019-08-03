package com.omeric.android.hearthisattopartists.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.omeric.android.hearthisattopartists.R
import com.omeric.android.hearthisattopartists.data.model.TrackModel
import com.squareup.picasso.Picasso
import com.omeric.android.hearthisattopartists.activity.MainActivity


abstract class MoviesAdapter(
    private val tracks: ArrayList<TrackModel>,
    private val rowLayout: Int
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>()
{
    companion object
    {
        private val TAG = "gipsy:" + this::class.java.name
    }

    /**
     * A [RecyclerView.ViewHolder] inner class where we get reference to the views in the layout using their ID
     * using the [RecyclerView.ViewHolder] pattern to make an object that holds all view references
     */
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        internal var movieItemLayout: ConstraintLayout = view.findViewById(R.id.movie_item_layout)
        internal var movieTitle: TextView = view.findViewById(R.id.title_list_item)
        internal var playbackCount: TextView = view.findViewById(R.id.textVw_playback_count_list_item)
        internal var downloadCount: TextView = view.findViewById(R.id.textVw_download_count_list_item2)
        internal var favoritingsCount: TextView = view.findViewById(R.id.textVw_favoritings_count_list_item)
        internal var posterImage: ImageView = view.findViewById(R.id.poster_image_list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
    {
        Log.d(TAG,"onCreateViewHolder:")
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * Inside the [onBindViewHolder] we check the type of ViewHolder instance and populate the row accordingly
     * Create the view holder for view items, connect the data source of the RecyclerView
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)
    {
//        Log.d(TAG,"onBindViewHolder:")
        if (tracks[position].user?.avatarUrl != null)
        {
//            Log.d(TAG, "onBindViewHolder: posterUrl = ${MainActivity.BASE_URL_MOVIE_POSTER + tracks[position].posterPath}")
            Picasso
                .get()
                .load(MainActivity.BASE_URL_MOVIE_POSTER + tracks[position].user!!.avatarUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.posterImage)
        }
        else {holder.posterImage.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)}

        holder.movieTitle.text = tracks[position].user!!.username
        holder.playbackCount.text = tracks[position].playbackCount
        holder.downloadCount.text = tracks[position].downloadCount
        holder.favoritingsCount.text = tracks[position].favoritingsCount

        holder.movieItemLayout.setOnClickListener {
            Log.d(TAG,"setOnClickListener:")
/*
            val context = holder.itemView.context
            (context as MainActivity).SetPlayerParams("", "", "")
*/
            playSongOfArtist(tracks[position].artworkUrl!!, tracks[position].title!!, tracks[position].streamUrl!!)
/*
            it.context.startActivity(Intent(it.context, DetailsActivity::class.java)
                .putExtra(DetailsActivity.INTENT_MOVIE_HOMEPAGE_URL, tracks[position].homePageUrl)
                .putExtra(DetailsActivity.INTENT_MOVIE_ID, tracks[position].id)
                .putExtra(DetailsActivity.INTENT_MOVIE_TITLE, tracks[position].originalTitle)
                .putExtra(DetailsActivity.INTENT_MOVIE_OVERVIEW, tracks[position].overview)
                .putExtra(DetailsActivity.INTENT_MOVIE_POPULARITY, tracks[position].popularity)
                .putExtra(DetailsActivity.INTENT_MOVIE_POSTER_PATH, tracks[position].posterPath)
                .putExtra(DetailsActivity.INTENT_MOVIE_RELEASE_DATE, tracks[position].downloadCount)
                .putExtra(DetailsActivity.INTENT_MOVIE_VOTE_AVERAGE, tracks[position].voteAverage)
                .putExtra(DetailsActivity.INTENT_MOVIE_VOTE_COUNT, tracks[position].voteCount)
            )
    */
        }
    }

    override fun getItemCount(): Int
    {
        return tracks.size
    }

    // TODO
    abstract fun playSongOfArtist(trackArtworkUrl : String, trackTitle : String, trackDataSourceUrl : String)
}
