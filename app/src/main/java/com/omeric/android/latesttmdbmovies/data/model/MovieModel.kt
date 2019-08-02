package com.omeric.android.latesttmdbmovies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieModel
{
/* dataset example:
  "homepage": "http://marvel.com/movies/movie/223/avengers_infinity_war_part_1",
  "id": 299536,
  "original_title": "Avengers: Infinity War",
  "overview": "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
  "popularity": 74.087,
  "poster_path": "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
  "release_date": "2018-04-25",
  "vote_average": 8.3,
  "vote_count": 14544
*/
    @SerializedName("homepage")
    @Expose
    var homePageUrl: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: /*Number*/Float? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: /*Number*/Float? = null

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
}
