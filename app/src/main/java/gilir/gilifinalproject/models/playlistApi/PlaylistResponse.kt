package gilir.gilifinalproject.models.playlistApi


import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    @SerializedName("data")
    val playlistList: List<Playlist>,
    val next: String,
    val total: Int
)