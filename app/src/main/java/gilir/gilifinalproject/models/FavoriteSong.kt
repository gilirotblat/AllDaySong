package gilir.gilifinalproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "fab songs")
data class FavoriteSong(
    @PrimaryKey
    val id: Long,
)