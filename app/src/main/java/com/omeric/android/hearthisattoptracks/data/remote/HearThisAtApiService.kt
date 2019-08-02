package com.omeric.android.hearthisattoptracks.data.remote

import com.omeric.android.hearthisattoptracks.data.model.TrackModel
import com.omeric.android.hearthisattoptracks.data.model.PopularTracksModel
import io.reactivex.Single
import retrofit2.http.*


/**
 * This interface defines methods used by Retrofit to communicate with a given API.
 * Each interface method, can be thought of as a RESTful method.
 */
interface HearThisAtApiService
{
    //example: https://api.themoviedb.org/3/movie/299536?api_key=1e0dcaa7e93980fb84e1d2430d01b887&language=en-US
    @GET("movie/{id}?api_key={apiKey}&language=en-US")
    fun getMovieDetails(
        @Path("id") id: String,
        @Path("apiKey") apiKey: String
    ): Single<TrackModel>

    //example: https://api-v2.hearthis.at/feed/?type=popular&page=1&count=20
    @GET("feed\n")
    fun getPopularTracks(
        @Query("type") type: String, //popular
        @Query("page") page: Int, //1
        @Query("count") count: Int //20
//    ): Single<PopularTracksModel>
    ): Single<ArrayList<TrackModel>>

    //example: https://api.themoviedb.org/3/discover/movie?api_key=1e0dcaa7e93980fb84e1d2430d01b887&language=en-US&sort_by=release_date.desc&include_adult=false&include_video=false&page=1&primary_release_date.lte=2013-08-30
    @GET("discover/movie")
    fun getMoviesFromDiscover(
        @QueryMap filters : Map<String, String>
    ): Single<PopularTracksModel>

    //example: "https://api.themoviedb.org/3/movie/latest?language=en-US&api_key=1e0dcaa7e93980fb84e1d2430d01b887"
    @GET("movie/latest?language=en-US&api_key={apiKey}")
    fun getLatestMovie(
        @Path("apiKey") apiKey: String
    ): Single<TrackModel>

    //example: https://api.github.com/search/repositories?q=topic:android&sort=stars&order=desc
/*
    @GET("search/repositories")
    fun getMoviesFromSearch(
        @QueryMap filters : Map<String, String>
    ): Single<PopularTracksModel>
*/
}