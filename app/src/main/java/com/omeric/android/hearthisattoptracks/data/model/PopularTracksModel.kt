package com.omeric.android.hearthisattoptracks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* dataset example:
[
  {
    "id":"1242574",
    "created_at":"2019-07-29 02:08:20",
    "release_date":"2019-07-29 02:08:20",
    "release_timestamp":1564358900,
    "user_id":"733490",
    "duration":"3601",
    "permalink":"househertz-overthinking-radio-show-burning-11",
    "description":"By HousehertZ Only and exclusively @NRJ Lebanon ",
    "geo":"",
    "tags":"",
    "taged_artists":"",
    "bpm":"128",
    "key":"",
    "license":"Noncommercial",
    "version":"",
    "type":"Podcast",
    "downloadable":"0",
    "genre":"EDM",
    "genre_slush":"edm",
    "title":"HousehertZ - Overthinking Radio Show Burning 11",
    "uri":"https:\/\/api-v2.hearthis.at\/househertz\/househertz-overthinking-radio-show-burning-11\/",
    "permalink_url":"https:\/\/hearthis.at\/househertz\/househertz-overthinking-radio-show-burning-11\/",
    "thumb":"https:\/\/images.hearthis.at\/1\/4\/8\/_\/uploads\/733490\/image_track\/1242574\/w200_h200_q70_----1489119004.jpg",
    "artwork_url":"https:\/\/images.hearthis.at\/1\/4\/8\/_\/uploads\/733490\/image_track\/1242574\/w500_q70_----1489119004.jpg",
    "artwork_url_retina":"https:\/\/images.hearthis.at\/1\/4\/8\/_\/uploads\/733490\/image_track\/1242574\/w1000_q70_----1489119004.jpg",
    "background_url":"https:\/\/images.hearthis.at\/1\/4\/8\/_\/uploads\/733490\/image_track\/1242574\/w565_h565_c000000_q70_----1489119004.jpg",
    "waveform_data":"https:\/\/hearthis.at\/_\/wave_data\/733490\/3000_b5842bf10995835d5bc71c4a2af84e59.mp3_1564358900.js",
    "waveform_url":"https:\/\/hearthis.at\/_\/cache\/waveform_mask\/1\/2\/1242574.png",
    "user":{
      "id":"733490",
      "permalink":"househertz",
      "username":"HousehertZ",
      "caption":"",
      "uri":"https:\/\/api-v2.hearthis.at\/househertz\/",
      "permalink_url":"https:\/\/hearthis.at\/househertz\/",
      "avatar_url":"https:\/\/images.hearthis.at\/c\/r\/o\/_\/uploads\/733490\/image_user\/w512_q70_m1477341165----cropped_623983d1c0e40ac00677be7b184bb65a146540410202814349184671208956835n.jpg"
    },
    "stream_url":"https:\/\/hearthis.at\/househertz\/househertz-overthinking-radio-show-burning-11\/listen\/?s=rNC",
    "preview_url":"https:\/\/preview.hearthis.at\/files\/b5842bf10995835d5bc71c4a2af84e59.mp3",
    "download_url":"",
    "download_filename":"HousehertZ - Overthinking Radio Show Burning 11 (hearthis.at).mp3",
    "playback_count":"624",
    "download_count":"6",
    "favoritings_count":"3",
    "reshares_count":"0",
    "comment_count":"0",
    "played":false,
    "favorited":false,
    "liked":false,
    "reshared":false
  },
  {
    "id":"3281651",
    "created_at":"2019-07-31 16:34:51",
    "release_date":"2019-07-31 16:34:51",
    "release_timestamp":1564583691,
    "user_id":"9273976",
    "duration":"3839",
    "permalink":"trap-district-deejay-azra",
    "description":"",
    "geo":"",
    "tags":"",
    "taged_artists":"",
    "bpm":"94",
    "key":"Dm",
    "license":"",
    "version":"",
    "type":"Mix",
    "downloadable":"1",
    "genre":"Trap",
    "genre_slush":"trap",
    "title":"TRAP DISTRICT -DEEJAY AZRA",
    "uri":"https:\/\/api-v2.hearthis.at\/azra-gats-xv\/trap-district-deejay-azra\/",
    "permalink_url":"https:\/\/hearthis.at\/azra-gats-xv\/trap-district-deejay-azra\/",
    "thumb":"https:\/\/images.hearthis.at\/1\/5\/6\/_\/uploads\/9273976\/image_track\/3281651\/w200_h200_q70_----1561053647911.jpg",
    "artwork_url":"https:\/\/images.hearthis.at\/1\/5\/6\/_\/uploads\/9273976\/image_track\/3281651\/w500_q70_----1561053647911.jpg",
    "artwork_url_retina":"https:\/\/images.hearthis.at\/1\/5\/6\/_\/uploads\/9273976\/image_track\/3281651\/w1000_q70_----1561053647911.jpg",
    "background_url":"https:\/\/images.hearthis.at\/1\/5\/6\/_\/uploads\/9273976\/image_track\/3281651\/w565_h565_c000000_q70_----1561053647911.jpg",
    "waveform_data":"https:\/\/hearthis.at\/_\/wave_data\/9273976\/3000_12f78ecec2f4c947dd93437cc27ee2a1.mp3_1564583691.js",
    "waveform_url":"https:\/\/hearthis.at\/_\/cache\/waveform_mask\/3\/2\/3281651.png",
    "user":{
      "id":"9273976",
      "permalink":"azra-gats-xv",
      "username":"Azra Gats",
      "caption":"",
      "uri":"https:\/\/api-v2.hearthis.at\/azra-gats-xv\/",
      "permalink_url":"https:\/\/hearthis.at\/azra-gats-xv\/",
      "avatar_url":"https:\/\/images.hearthis.at\/1\/5\/6\/_\/uploads\/9273976\/image_user\/w512_q70_----1564650664517.jpg"
    },
    "stream_url":"https:\/\/hearthis.at\/azra-gats-xv\/trap-district-deejay-azra\/listen\/?s=Jw5",
    "preview_url":"https:\/\/preview.hearthis.at\/files\/12f78ecec2f4c947dd93437cc27ee2a1.mp3",
    "download_url":"https:\/\/hearthis.at\/azra-gats-xv\/trap-district-deejay-azra\/download\/",
    "download_filename":"TRAP DISTRICT -DEEJAY AZRA (hearthis.at).mp3",
    "playback_count":"210",
    "download_count":"41",
    "favoritings_count":"6",
    "reshares_count":"0",
    "comment_count":"0",
    "played":false,
    "favorited":false,
    "liked":false,
    "reshared":false
  }
]
*/

class PopularTracksModel
{
    @SerializedName("results")
    @Expose
    var results: ArrayList<TrackModel>? = null
}


