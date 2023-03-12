package gilir.gilifinalproject.models.playlistsong


import com.google.gson.annotations.SerializedName

data class PlaylistSongResponse(
    @SerializedName("data")
    val playlistSong: List<PlaylistSong>
)