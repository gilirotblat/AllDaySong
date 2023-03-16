package gilir.gilifinalproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "fab songs")
data class FavoriteSong (
    @PrimaryKey
    val id :Long,
        )