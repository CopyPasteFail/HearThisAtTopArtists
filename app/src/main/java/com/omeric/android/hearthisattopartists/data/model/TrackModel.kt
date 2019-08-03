package com.omeric.android.hearthisattopartists.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TrackModel
{
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("artwork_url")
    @Expose
    var artworkUrl: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("stream_url")
    @Expose
    var streamUrl: String? = null
    @SerializedName("playback_count")
    @Expose
    var playbackCount: String? = null
    @SerializedName("download_count")
    @Expose
    var downloadCount: String? = null
    @SerializedName("favoritings_count")
    @Expose
    var favoritingsCount: String? = null

    class User
    {
        @SerializedName("username")
        @Expose
        var username: String? = null
        @SerializedName("avatar_url")
        @Expose
        var avatarUrl: String? = null
    }
}
