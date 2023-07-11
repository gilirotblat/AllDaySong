package gilir.gilifinalproject.ui



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gilir.gilifinalproject.Application
import gilir.gilifinalproject.models.songsapi.Song
import gilir.gilifinalproject.repository.Songs
import gilir.gilifinalproject.service.AppService
import kotlinx.coroutines.launch

class FullPageViewModel : ViewModel() {

    private val _artistSongs: MutableLiveData<Songs> = MutableLiveData()
    val artistSongs: LiveData<Songs> = _artistSongs


    private val _playlistSongs: MutableLiveData<Songs> = MutableLiveData()
    val playlistSongs: LiveData<Songs> = _playlistSongs
    val favoriteSongs: LiveData<List<Song>> = Application.repository.getFavoriteSongsLive()
    fun deleteSong(song: Song){
        viewModelScope.launch {
            Application.repository.deleteSong(song)
        }
    }

    suspend fun favoritesMapping() : HashMap<Long,Boolean> {
        val favoritesSongs = Application.repository.getFavoriteSongs()
        val favoritesMapping = HashMap<Long,Boolean>()
        favoritesSongs.forEach { song ->
            favoritesMapping[song.id] = true
        }
        return favoritesMapping
    }

    fun getArtistSongs(artistId: Long) {
        viewModelScope.launch {
            val songList = AppService.create().getArtistSongs(artistId).songs
            val favoritesMapping = favoritesMapping()
            _artistSongs.postValue(Songs(songList.toMutableList(), favoritesMapping))
        }
    }

        fun getPlaylistSongs(playlistId: String) {
            viewModelScope.launch {
                val songList = AppService.create().getTracklistPlaylist(playlistId).songs
                val favoritesMapping = favoritesMapping()
                _playlistSongs.postValue(Songs(songList.toMutableList(), favoritesMapping))
            }
        }

    fun addSongToFavorites(song: Song) {
        viewModelScope.launch {
            Application.repository.addSongToFavorites(song)
        }
    }
}