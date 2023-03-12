package gilir.gilifinalproject.models.artistapi


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey
    @SerializedName("id")
    val artistId: Long,
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
    val position: Int,
    val radio: Boolean,
    val tracklist: String,
    val type: String
):Parcelable
