package gilir.gilifinalproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gilir.gilifinalproject.Application
import gilir.gilifinalproject.models.songsapi.Song
import kotlinx.coroutines.launch

class FullPageViewModel : ViewModel(), ViewModelSongUpdater {

    private val _artistSongs: MutableLiveData<List<Song>> = MutableLiveData()
    val artistSongs: LiveData<List<Song>> = _artistSongs


    private val _playlistSongs: MutableLiveData<List<Song>> = MutableLiveData()
    val playlistSongs: LiveData<List<Song>> = _playlistSongs



    override fun updateSong(song: Song) {
        viewModelScope.launch {
            Application.repository.update(song)
        }
    }

    override fun addSongToFavorites(song: Song) {
        viewModelScope.launch {
            Application.repository.addSongToFavorites(song).also {
                Log.d("TAG", "addSongToFavorites: $it")
            }
        }
    }

    fun getArtistSongs(artistId: Long) {
        viewModelScope.launch {
         val artistSongs = Application.repository.getArtistSongs(artistId)
            _artistSongs.postValue(artistSongs.songs)

        }
    }



        fun getPlaylistSongs(playlistId: String) {
            viewModelScope.launch {
                val playlistSong = Application.repository.getPlaylistSongs(playlistId)
                _playlistSongs.postValue(playlistSong.songs)
            }
        }
    }