package hr.algebra.lmandic.procvat.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val API_URL = "https://pixabay.com/api/"

interface PixabayApi {
    @GET("?key=22968610-c9b6304f0476f027fd1069a8e&q={query}&image_type=photo")
    fun searchImage(@Path("query") query: String) : Call<List<PixabayItem>>
}