# HearThisAtTopTracks
An android app that shows the top artists from HearThisAt with infinite scrolling and minimalistic audio player.

The app that displays a list of artists and songs using the HeartThisAt API.
The first level of content displays a list of top artists (with image and further details, i.e.  track count).
When an artist is selected, the corresponding song (with image and details, i.e. song duration) will be played.


# Introduction
This Android App that has 1 Screen: 
1. A list of the top artists (by top tracks)


## Table of content
- [How It Works](#How-It-Works)
- [Installation](#Installation)
- [Maintainers](#Maintainers)
- [Contributing](#Contributing)
- [License](#license)
- [Links](#links)


## How It Works
Using Retrofit2 for the network call to TMDb API and RxAndroid for asynchronous calls, and Android's MediaPlayer
for streaming


## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/GipsyBeggar/HearThisAtTopTracks.git
```


## Maintainers
This project is mantained by:
* [Omer Reznik](http://github.com/GipsyBeggar)


## Contributing
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request


## License
This project is licensed under the GNU Affero General Public License v3.0 - see the [LICENSE.md](LICENSE.md) file for details


## Links
The following articles and documentations have been used:
### Network calls and JSON
- https://proandroiddev.com/tips-for-refactoring-to-rxjava-2-with-kotlin-b88dd8cafe08
- https://proandroiddev.com/modern-android-development-with-kotlin-part-3-8721fb843d1b
- https://medium.com/@elye.project/the-benefits-of-rxjava-example-in-kotlin-fbe9a3fee7
- https://www.raywenderlich.com/384-reactive-programming-with-rxandroid-in-kotlin-an-introduction
- https://code.tutsplus.com/tutorials/kotlin-reactive-programming-with-rxjava-and-rxkotlin--cms-31577
- https://android.jlelse.eu/keddit-part-5-kotlin-rxjava-rxandroid-105f95bfcd22

### Endless Scrolling
- https://gist.github.com/ssinss/e06f12ef66c51252563e
- https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews-and-RecyclerView
- https://www.supinfo.com/articles/single/7849-developing-popular-movies-application-in-android-using-retrofit
- https://gist.github.com/nesquena/d09dc68ff07e845cc622
- https://stackoverflow.com/questions/48237141/how-to-infinite-scroll-load-more-with-recycleview

### MediaPlayer
- https://medium.com/@ssaurel/implement-audio-streaming-in-android-applications-8758d3bc62f1
- https://developer.android.com/reference/kotlin/android/media/MediaPlayer#StateDiagram
- https://developer.android.com/guide/topics/media/mediaplayer
- https://developer.android.com/reference/android/media/MediaPlayer
- https://coding180.com/kotlin-android/playing-pausing-resuming-and-stopping-an-audio-file/
- https://coding180.com/kotlin-android/playback-file-located-on-the-internet/
- https://medium.com/androiddevelopers/building-a-simple-audio-app-in-android-part-1-3-c14d1a66e0f1
- https://android--code.blogspot.com/2018/05/android-kotlin-media-player-seekbar.html
- https://www.tutorialspoint.com/android/android_mediaplayer.htm
- https://code.tutsplus.com/tutorials/create-a-music-player-on-android-user-controls--mobile-22787
- https://developer.android.com/guide/topics/media/media-formats