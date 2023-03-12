package gilir.gilifinalproject.models.artistaong


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import gilir.gilifinalproject.models.songsapi.Album
import gilir.gilifinalproject.models.songsapi.AlbumConverter
import gilir.gilifinalproject.models.songsapi.ArtistConverter
import gilir.gilifinalproject.models.songsapi.SongArtist


@Entity(tableName = "artist songs")
data class ArtistSong(
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
    @PrimaryKey
    val id: Int,
    val link: String,
    @SerializedName("md5_image")
    val md5Image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String?,
    val type: String,
    var isClicked :Boolean
)