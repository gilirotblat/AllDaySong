package gilir.gilifinalproject.models.artistaong


import com.google.gson.annotations.SerializedName

data class Album(
    val cover: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    val id: Int,
    @SerializedName("md5_image")
    val md5Image: String,
    val title: String,
    val tracklist: String,
    val type: String
)