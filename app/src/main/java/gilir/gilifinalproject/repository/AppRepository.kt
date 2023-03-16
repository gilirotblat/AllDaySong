package gilir.gilifinalproject.repository


import android.util.Log
import gilir.gilifinalproject.data.dao.SongDao
import gilir.gilifinalproject.models.FavoriteSong
import gilir.gilifinalproject.models.songsapi.Song
import gilir.gilifinalproject.models.songsapi.SongResponse
import gilir.gilifinalproject.service.AppService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(private val songDao: SongDao) {

    suspend fun getSongs(): List<Song> = withContext(Dispatchers.IO) {
        val songs = AppService.create().getSongs().songs
        val favoriteSongs = songDao.getAllFavoriteSongs()
        songs.forEach { song ->
            favoriteSongs.forEach { favoriteSong ->
                if (song.id == favoriteSong.id) {
                    song.isFavorite = true
                }
            }
        }
        songs
    }

    suspend fun getArtists() = withContext(Dispatchers.IO) {
        AppService.create().getArtists().artists
    }

    suspend fun getPlaylists() = withContext(Dispatchers.IO) {
        AppService.create().getPlaylist().playlistList
    }

    suspend fun getAllFavoriteSongs() = withContext(Dispatchers.IO) {
        songDao.getAllFavoriteSongs()
    }

    suspend fun update(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.updateSong(song)
        }
    }

    suspend fun addSongToFavorites(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.addFavoriteSong(FavoriteSong(song.id)).also {
                Log.d("TAG", "addSongToFavorites: $it")
            }
        }
    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val songs = AppService.create().getSongs().songs
            //מיותר
            songDao.addAllSongs(songs)

            val artists = AppService.create().getArtists().artists
            //מיותר
            songDao.addAllArtists(artists)

            val playlists = AppService.create().getPlaylist().playlistList
            //מיותר
            songDao.addAllPlaylists(playlists)

        }
    }

    suspend fun getArtistSongs(artistId: Long): SongResponse {
        val artistSong = AppService.create().getArtistSongs(artistId)
        songDao.addAllArtistSongs(artistSong.songs)
        return withContext(Dispatchers.IO) {
            artistSong
        }
    }

    suspend fun getPlaylistSongs(playlistId: String): SongResponse {
        val playlistSong = AppService.create().getTracklistPlaylist(playlistId)
        songDao.addAllPlaylistsSong(playlistSong.songs)
        return withContext(Dispatchers.IO) {
            playlistSong
        }
    }


}



