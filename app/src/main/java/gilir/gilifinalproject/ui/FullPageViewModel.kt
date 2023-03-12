package gilir.gilifinalproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gilir.gilifinalproject.Application
import gilir.gilifinalproject.models.artistaong.ArtistSong
import gilir.gilifinalproject.models.playlistsong.PlaylistSong
import gilir.gilifinalproject.models.songsapi.Song
import kotlinx.coroutines.launch

class FullPageViewModel : ViewModel() {

    private val _artistSongs: MutableLiveData<List<ArtistSong>> = MutableLiveData()
    val artistSongs: LiveData<List<ArtistSong>> = _artistSongs




    private val _playlistSongs: MutableLiveData<List<PlaylistSong>> = MutableLiveData()
    val playlistSongs: LiveData<List<PlaylistSong>> = _playlistSongs

    fun updateSong(song: PlaylistSong) {
        viewModelScope.launch {
            Application.repository.updatePlSong(song)
        }
    }


    fun updateASong(song: ArtistSong) {
        viewModelScope.launch {
            Application.repository.updateASong(song)
        }
    }

    fun getArtistSongs(artistId: String) {
        viewModelScope.launch {
         val artistSongs = Application.repository.getArtistSongs(artistId)
            _artistSongs.postValue(artistSongs.artistSongList)
        }
    }



        fun getPlaylistSongs(playlistId: String) {
            viewModelScope.launch {
                val playlistSong = Application.repository.getPlaylistSongs(playlistId)
                _playlistSongs.postValue(playlistSong.playlistSong)
            }
        }
    }