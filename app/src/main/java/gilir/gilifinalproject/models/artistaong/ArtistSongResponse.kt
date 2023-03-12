package gilir.gilifinalproject.models.artistaong


import com.google.gson.annotations.SerializedName



data class ArtistSongResponse(
    @SerializedName("data")
    val artistSongList: List<ArtistSong>,
    val next: String,
    val total: Int
)