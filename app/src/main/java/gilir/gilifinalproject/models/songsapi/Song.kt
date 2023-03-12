package gilir.gilifinalproject.models.songsapi


import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    val id: Long,
    @TypeConverters(AlbumConverter::class)
    val album: Album,
    @TypeConverters(ArtistConverter::class)
    val artist: SongArtist,
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    val link: String,
    @SerializedName("md5_image")
    val md5Image: String,
    val position: Int?,
    val preview: String,
    val rank: Int,
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    val type: String,
    var isClicked :Boolean
)




