package com.omeric.android.hearthisattoptracks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* dataset example:
{
   "id":"48250",
   "created_at":"2014-07-06 13:05:10",
   "release_date":"2014-07-05 00:00:00",
   "release_timestamp":1404511200,
   "user_id":"7",
   "duration":"7376",
   "permalink":"shawne-back-to-the-roots-2-05072014",
   "description":"Years: 2000 - 2005\nSet Time: Warm Up (11 pm - 01 am)",
   "geo":"Blankenburgstra\u00dfe 62, 09114 Chemnitz, Deutschland",
   "tags":"n*dorphinclub,Fraktion42,Shawne",
   "taged_artists":"Shawne (hearthis.at)",
   "bpm":"0",
   "key":"",
   "license":"",
   "version":"",
   "type":"DJ-Set",
   "downloadable":0,
   "genre":"Drum & Bass",
   "genre_slush":"drumandbass",
   "title":"Shawne @ Back To The Roots 2 (05.07.2014)",
   "uri":"https:\/\/api-v2.hearthis.at\/shawne\/shawne-back-to-the-roots-2-05072014\/",
   "permalink_url":"https:\/\/hearthis.at\/shawne\/shawne-back-to-the-roots-2-05072014\/",
   "thumb":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/7\/image_track\/48250\/w200_h200_q70_m1477341072----cropped_eb1f6964a0af801c68f2431b87b65b21black-32390640.png",
   "artwork_url":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/7\/image_track\/48250\/w500_q70_m1477341072----cropped_eb1f6964a0af801c68f2431b87b65b21black-32390640.png",
   "artwork_url_retina":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/7\/image_track\/48250\/w1000_q70_m1477341072----cropped_eb1f6964a0af801c68f2431b87b65b21black-32390640.png",
   "background_url":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/7\/image_track\/48250\/w565_h565_c000000_q70_m1477341072----cropped_eb1f6964a0af801c68f2431b87b65b21black-32390640.png",
   "waveform_data":"https:\/\/hearthis.at\/_\/wave_data\/7\/3000_4382f398c454c47cf171aab674cf00f0.mp3_1404644710.js",
   "waveform_url":"https:\/\/hearthis.at\/_\/cache\/waveform_mask\/4\/8\/48250.png",
   "user":{
      "id":"7",
      "permalink":"shawne",
      "username":"Shawne",
      "caption":"Founder of hearthis.at",
      "uri":"https:\/\/api-v2.hearthis.at\/shawne\/",
      "permalink_url":"https:\/\/hearthis.at\/shawne\/",
      "avatar_url":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/7\/image_user\/w512_q70_m1538163559----cropped_1538163556635.jpg"
   },
   "stream_url":"https:\/\/hearthis.at\/shawne\/shawne-back-to-the-roots-2-05072014\/listen\/?s=w3H",
   "preview_url":"",
   "download_url":"",
   "download_filename":"Shawne @ Back To The Roots 2 (05.07.2014).mp3",
   "playback_count":"1846",
   "download_count":"14",
   "favoritings_count":"18",
   "reshares_count":"7",
   "comment_count":"1",
   "played":false,
   "favorited":false,
   "liked":false,
   "reshared":false
}
*/

class TrackModel
{
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("duration")
    @Expose
    var duration: String? = null
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("downloadable")
    @Expose
    var downloadable: String? = null
    @SerializedName("genre")
    @Expose
    var genre: String? = null
    @SerializedName("genre_slush")
    @Expose
    var genreSlush: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null
    @SerializedName("permalink_url")
    @Expose
    var permalinkUrl: String? = null
    @SerializedName("artwork_url")
    @Expose
    var artworkUrl: String? = null
    @SerializedName("background_url")
    @Expose
    var backgroundUrl: String? = null
    @SerializedName("waveform_data")
    @Expose
    var waveformData: String? = null
    @SerializedName("waveform_url")
    @Expose
    var waveformUrl: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("stream_url")
    @Expose
    var streamUrl: String? = null
    @SerializedName("download_url")
    @Expose
    var downloadUrl: String? = null
    @SerializedName("playback_count")
    @Expose
    var playbackCount: String? = null
    @SerializedName("download_count")
    @Expose
    var downloadCount: String? = null
    @SerializedName("favoritings_count")
    @Expose
    var favoritingsCount: String? = null
    @SerializedName("favorited")
    @Expose
    var favorited: Boolean? = null
    @SerializedName("comment_count")
    @Expose
    var commentCount: String? = null

    class User
    {
        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("permalink")
        @Expose
        var permalink: String? = null
        @SerializedName("username")
        @Expose
        var username: String? = null
        @SerializedName("caption")
        @Expose
        var caption: String? = null
        @SerializedName("uri")
        @Expose
        var uri: String? = null
        @SerializedName("permalink_url")
        @Expose
        var permalinkUrl: String? = null
        @SerializedName("avatar_url")
        @Expose
        var avatarUrl: String? = null
    }
}
