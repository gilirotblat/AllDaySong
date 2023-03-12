package gilir.gilifinalproject.models.songsapi

import com.google.gson.annotations.SerializedName


data class SongResponse(
    @SerializedName("data")
    val songs: List<Song>,
    val total: Int
)