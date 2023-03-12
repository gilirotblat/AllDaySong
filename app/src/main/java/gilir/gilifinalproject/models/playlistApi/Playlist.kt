package gilir.gilifinalproject.models.playlistApi


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Playlist(
    @PrimaryKey
    val id: Long,
    @SerializedName("md5_image")
    val md5Image: String,
    val picture: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    val title: String,
    val tracklist: String,
    val type: String,
    var isClicked :Boolean
): Parcelable