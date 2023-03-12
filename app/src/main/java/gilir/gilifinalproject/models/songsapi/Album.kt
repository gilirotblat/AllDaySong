package gilir.gilifinalproject.models.songsapi


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName


data class Album(
    val id: Int,
    val cover: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("md5_image")
    val md5Image: String,
    val title: String,
    val tracklist: String,
    val type: String
)

@ProvidedTypeConverter
class AlbumConverter {
    @TypeConverter
    fun stringToAlbum(string: String): Album {
        val list = string.split("+")
        return list.let {
            Album(
                it[0].toInt(),
                it[1],
                it[2],
                it[3],
                it[4],
                it[5],
                it[6],
                it[7],
                it[8],
                it[9]
            )
        }
    }

    @TypeConverter
    fun albumToString(album: Album): String {
        val albumString = StringBuilder().apply {
            append(album.id)
            append("+")
            append(album.cover)
            append("+")
            append(album.coverBig)
            append("+")
            append(album.coverMedium)
            append("+")
            append(album.coverSmall)
            append("+")
            append(album.coverXl)
            append("+")
            append(album.md5Image)
            append("+")
            append(album.title)
            append("+")
            append(album.tracklist)
            append("+")
            append(album.type)
        }.toString()
        return albumString
    }
}