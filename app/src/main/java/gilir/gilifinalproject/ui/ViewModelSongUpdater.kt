package gilir.gilifinalproject.ui

import gilir.gilifinalproject.models.songsapi.Song

interface ViewModelSongUpdater {
    fun updateSong(song: Song)
    fun addSongToFavorites(song: Song)
}