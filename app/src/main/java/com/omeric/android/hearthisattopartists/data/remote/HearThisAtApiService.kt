package com.omeric.android.hearthisattopartists.data.remote

import com.omeric.android.hearthisattopartists.data.model.TrackModel
import io.reactivex.Single
import retrofit2.http.*


/**
 * This interface defines methods used by Retrofit to communicate with a given API.
 * Each interface method, can be thought of as a RESTful method.
 */
interface HearThisAtApiService
{
    //example: https://api-v2.hearthis.at/feed/?type=popular&page=1&count=20
    @GET("feed\n")
    fun getPopularTracks(
        @Query("type") type: String, //popular
        @Query("page") page: Int, //1
        @Query("count") count: Int //20
    ): Single<ArrayList<TrackModel>>
}
