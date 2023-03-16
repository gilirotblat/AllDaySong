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
import gilir.gilifinalproject.service.AppService


import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _songs: MutableLiveData<List<Song>> = MutableLiveData()
    val songs: LiveData<List<Song>> = _songs


    private val _artist: MutableLiveData<List<Artist>> = MutableLiveData()
    val artist: LiveData<List<Artist>> = _artist

    private val _playlist: MutableLiveData<List<Playlist>> = MutableLiveData()
    val playlist: LiveData<List<Playlist>> = _playlist

    //val favoriteSongs= Application.repository.getAllFavoriteSongs()
    private val _favoriteSongs: MutableLiveData<List<Song>> = MutableLiveData()
    val favoriteSongs: LiveData<List<Song>> = _favoriteSongs


    init {
        viewModelScope.launch {
            _songs.postValue(AppService.create().getSongs().songs)
            _artist.postValue(AppService.create().getArtists().artists)
            _playlist.postValue(AppService.create().getPlaylist().playlistList)
            _favoriteSongs.postValue(Application.repository.getAllFavoriteSongs())
        }
    }


    fun updateSong(song: Song) {
        viewModelScope.launch {
            Application.repository.update(song)
        }
    }

    fun refresh(swiperefresh: SwipeRefreshLayout?) {
        //להוסיף בדיקת אינטרנט
        viewModelScope.launch {
            Application.repository.refresh()
            _songs.postValue(AppService.create().getSongs().songs)
            _artist.postValue(AppService.create().getArtists().artists)
            _playlist.postValue(AppService.create().getPlaylist().playlistList)
            swiperefresh?.isRefreshing = false

        }
    }


}



