package gilir.gilifinalproject.repository


import gilir.gilifinalproject.data.dao.SongDao
import gilir.gilifinalproject.models.artistaong.ArtistSong
import gilir.gilifinalproject.models.artistaong.ArtistSongResponse
import gilir.gilifinalproject.models.playlistsong.PlaylistSong
import gilir.gilifinalproject.models.playlistsong.PlaylistSongResponse
import gilir.gilifinalproject.models.songsapi.Song
import gilir.gilifinalproject.service.SongService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SongRepository(private val songDao: SongDao) {

    suspend fun getSongs() = songDao.getAllSongsNotLive()
    fun getArtists() = songDao.getAllArtists()
    fun getPlaylists() = songDao.getAllPlaylists()
    fun getFavoriteSong() = songDao.getFavoriteSong()



    suspend fun updateASong(song: ArtistSong) {
        withContext(Dispatchers.IO) {
            songDao.updateASong(song)
        }
    }

    suspend fun updatePlSong(song: PlaylistSong) {
        withContext(Dispatchers.IO) {
            songDao.updatePlSong(song)
        }
    }

    suspend fun update(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.updateSong(song)
        }

    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val songs = SongService.create().getSongs().songs
            songDao.addAllSongs(songs)

            val artists = SongService.create().getArtists().artists
            songDao.addAllArtists(artists)


            val playlists = SongService.create().getPlaylist().radioList
            songDao.addAllPlaylists(playlists)
        }
    }

    suspend fun getArtistSongs(artistId: String): ArtistSongResponse {
        val artistSong = SongService.create().getArtistSongs(artistId)
        songDao.addAllArtistSongs(artistSong.artistSongList)
        return withContext(Dispatchers.IO) {
            artistSong
        }
    }



    suspend fun getPlaylistSongs(playlistId: String): PlaylistSongResponse {
        val playlistSong = SongService.create().getTracklistPlaylist(playlistId)
        songDao.addAllPlaylistsSong(playlistSong.playlistSong)
        return withContext(Dispatchers.IO) {
            playlistSong
        }
    }


}



