package gilir.gilifinalproject.models.playlistsong


import com.google.gson.annotations.SerializedName

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val tracklist: String,
    val type: String
)