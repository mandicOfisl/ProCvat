package hr.algebra.lmandic.procvat.api

import com.google.gson.annotations.SerializedName

data class PixabayItem (
    @SerializedName("total") val total : Int,
    @SerializedName("totalHits") val totalHits : Int,
    @SerializedName("hits") val hits : List<Hits>
)