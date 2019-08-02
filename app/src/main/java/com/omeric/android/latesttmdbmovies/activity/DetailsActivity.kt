package com.omeric.android.latesttmdbmovies.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.omeric.android.latesttmdbmovies.R
import com.squareup.picasso.Picasso

/**
 * This activity shows the details of the movie when the user clicks on it from the main activity
 */
class DetailsActivity : AppCompatActivity()
{
    companion object
    {
        private val TAG = "gipsy:" + this::class.java.name
        const val INTENT_MOVIE_HOMEPAGE_URL = "MOVIE_HOMEPAGE_URL"
        const val INTENT_MOVIE_ID = "MOVIE_ID"
        const val INTENT_MOVIE_TITLE = "MOVIE_TITLE"
        const val INTENT_MOVIE_OVERVIEW = "MOVIE_OVERVIEW"
        const val INTENT_MOVIE_POPULARITY = "MOVIE_POPULARITY"
        const val INTENT_MOVIE_POSTER_PATH = "MOVIE_POSTER_PATH"
        const val INTENT_MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE"
        const val INTENT_MOVIE_VOTE_AVERAGE = "MOVIE_VOTE_AVERAGE"
        const val INTENT_MOVIE_VOTE_COUNT = "MOVIE_VOTE_COUNT"
    }

    private lateinit var detailsLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        Log.d(TAG, ":onCreate")

        val movieTitle: TextView = findViewById(R.id.TextVw_movie_details_title)
        val movieOverview: TextView = findViewById(R.id.TextVw_movie_details_overview)
        val popularity: TextView = findViewById(R.id.TextVw_movie_details_popularity)
        val voteAverage: TextView = findViewById(R.id.TextVw_movie_details_vote_average)
        val voteCount: TextView = findViewById(R.id.TextVw_movie_details_vote_count)
        val posterImage: ImageView = findViewById(R.id.ImgVw_movie_details_poster_image)
        val homepageUrl: TextView = findViewById(R.id.TextVw_movie_homepage_url)
        val releaseDate: TextView = findViewById(R.id.TextVw_movie_details_release_date)
        val posterPath = intent.getStringExtra(INTENT_MOVIE_POSTER_PATH)

        detailsLayout = findViewById(R.id.movie_details_layout)

        if (intent.getStringExtra(INTENT_MOVIE_HOMEPAGE_URL) == null)
            {findViewById<ImageView>(R.id.ImgVw_movie_details_link).visibility = View.INVISIBLE}
        else {homepageUrl.text = intent.getStringExtra(INTENT_MOVIE_HOMEPAGE_URL)}
        movieTitle.text = intent.getStringExtra(INTENT_MOVIE_TITLE)
        movieOverview.text = intent.getStringExtra(INTENT_MOVIE_OVERVIEW)
        popularity.text = (intent.getFloatExtra(INTENT_MOVIE_POPULARITY, 0.0f)).toString()
        releaseDate.text = intent.getStringExtra(INTENT_MOVIE_RELEASE_DATE)
        voteAverage.text = (intent.getFloatExtra(INTENT_MOVIE_VOTE_AVERAGE, 0.0f)).toString()
        voteCount.text = (intent.getIntExtra(INTENT_MOVIE_VOTE_COUNT, 0)).toString()

        Log.d(TAG, ":onCreate: popularity = ${(intent.getFloatExtra(INTENT_MOVIE_POPULARITY, 0.0f))}/")
        Log.d(TAG, ":onCreate: voteCount = ${(intent.getIntExtra(INTENT_MOVIE_VOTE_COUNT, 0))}/")
        Log.d(TAG, ":onCreate: voteAverage = ${(intent.getFloatExtra(INTENT_MOVIE_VOTE_AVERAGE, 0.0f))}/")
        Log.d(TAG, "onBindViewHolder: posterUrl = ${MainActivity.BASE_URL_MOVIE_POSTER + posterPath}")

        if (posterPath != null)
        {
            Picasso
                .get()
                .load(MainActivity.BASE_URL_MOVIE_POSTER + posterPath)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(posterImage)
        }
    }
}
