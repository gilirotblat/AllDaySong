package gilir.gilifinalproject.repository


import gilir.gilifinalproject.data.dao.SongDao
import gilir.gilifinalproject.models.songsapi.Song
import gilir.gilifinalproject.models.songsapi.SongResponse
import gilir.gilifinalproject.service.AppService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(private val songDao: SongDao) {

    suspend fun getSongs() = songDao.getAllSongsNotLive()
    fun getArtists() = songDao.getAllArtists()
    fun getPlaylists() = songDao.getAllPlaylists()
    suspend fun getAllFavoriteSongs() = songDao.getAllFavoriteSongs()



    suspend fun update(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.updateSong(song)
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

           val favoriteSongs = songDao.getAllFavoriteSongs()
            songDao.addFavoriteSongs(favoriteSongs)
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



