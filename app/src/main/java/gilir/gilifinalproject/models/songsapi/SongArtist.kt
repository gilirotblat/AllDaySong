package gilir.gilifinalproject.models.songsapi


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName



data class SongArtist(
    @SerializedName("id")
    val artistId: Int,
    val link: String,
    val name: String,
    val picture: String?,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)

@ProvidedTypeConverter
class ArtistConverter {

    @TypeConverter
    fun strToArtist(string: String): SongArtist {
        val list = string.split("+")

        return list.let {
            SongArtist(
                it[0].toInt(),
                it[1],
                it[2],
                it[3],
                it[4],
                it[5],
                it[6],
                it[7],
                it[8].toBoolean(),
                it[9],
                it[10]
            )
        }
    }
    @TypeConverter
    fun artistToStr (artist: SongArtist):String{
        val str = StringBuilder().apply {
            append(artist.artistId)
            append("+")
            append(artist.link)
            append("+")
            append(artist.name)
            append("+")
            append(artist.picture)
            append("+")
            append(artist.pictureBig)
            append("+")
            append(artist.pictureMedium)
            append("+")
            append(artist.pictureSmall)
            append("+")
            append(artist.pictureXl)
            append("+")
            append(artist.radio)
            append("+")
            append(artist.tracklist)
            append("+")
            append(artist.type)
        }.toString()
        return str
    }

}