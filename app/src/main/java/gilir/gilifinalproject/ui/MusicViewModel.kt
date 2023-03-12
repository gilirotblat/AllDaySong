package gilir.gilifinalproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gilir.gilifinalproject.Application
import gilir.gilifinalproject.models.artistapi.Artist
import gilir.gilifinalproject.models.playlistApi.Playlist
import gilir.gilifinalproject.models.songsapi.Song


import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    private val _songs: MutableLiveData<List<Song>> = MutableLiveData()
    val songs: LiveData<List<Song>> = _songs

//moshi

    val artist: LiveData<List<Artist>> = Application.repository.getArtists()
    val playlist: LiveData<List<Playlist>> = Application.repository.getPlaylists()
    val favoriteSong: LiveData<List<Song>> = Application.repository.getFavoriteSong()




   init {
       viewModelScope.launch {
       _songs.postValue(Application.repository.getSongs())
   }}


    fun updateSong(song: Song) {

        viewModelScope.launch {
            Application.repository.update(song)
        }
    }

    fun refresh(swiperefresh: SwipeRefreshLayout?) {
        //להוסיף בדיקת אינטרנט
        viewModelScope.launch {
            Application.repository.refresh()

            swiperefresh?.isRefreshing = false
        }
    }



}



