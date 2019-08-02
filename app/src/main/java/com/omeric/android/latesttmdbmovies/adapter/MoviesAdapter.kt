package com.omeric.android.latesttmdbmovies.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.omeric.android.latesttmdbmovies.R
import com.omeric.android.latesttmdbmovies.data.model.MovieModel
import com.squareup.picasso.Picasso
import android.content.Intent
import com.omeric.android.latesttmdbmovies.activity.DetailsActivity
import com.omeric.android.latesttmdbmovies.activity.MainActivity


class MoviesAdapter(
    private val movies: ArrayList<MovieModel>,
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
        internal var releaseDate: TextView = view.findViewById(R.id.TextVw_release_date_list_item)
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
        if (movies[position].posterPath != null)
        {
//            Log.d(TAG, "onBindViewHolder: posterUrl = ${MainActivity.BASE_URL_MOVIE_POSTER + movies[position].posterPath}")
            Picasso
                .get()
                .load(MainActivity.BASE_URL_MOVIE_POSTER + movies[position].posterPath)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.posterImage)
        }
        else {holder.posterImage.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)}

        holder.movieTitle.text = movies[position].originalTitle
        holder.releaseDate.text = movies[position].releaseDate

        holder.movieItemLayout.setOnClickListener {
            it.context.startActivity(Intent(it.context, DetailsActivity::class.java)
                .putExtra(DetailsActivity.INTENT_MOVIE_HOMEPAGE_URL, movies[position].homePageUrl)
                .putExtra(DetailsActivity.INTENT_MOVIE_ID, movies[position].id)
                .putExtra(DetailsActivity.INTENT_MOVIE_TITLE, movies[position].originalTitle)
                .putExtra(DetailsActivity.INTENT_MOVIE_OVERVIEW, movies[position].overview)
                .putExtra(DetailsActivity.INTENT_MOVIE_POPULARITY, movies[position].popularity)
                .putExtra(DetailsActivity.INTENT_MOVIE_POSTER_PATH, movies[position].posterPath)
                .putExtra(DetailsActivity.INTENT_MOVIE_RELEASE_DATE, movies[position].releaseDate)
                .putExtra(DetailsActivity.INTENT_MOVIE_VOTE_AVERAGE, movies[position].voteAverage)
                .putExtra(DetailsActivity.INTENT_MOVIE_VOTE_COUNT, movies[position].voteCount)
            )
        }
    }

    override fun getItemCount(): Int
    {
        return movies.size
    }
}
