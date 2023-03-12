package gilir.gilifinalproject.models.artistapi

import com.google.gson.annotations.SerializedName


data class ArtistsResponse(
    @SerializedName("data")
    val artists: List<Artist>,
    val total: Int
)