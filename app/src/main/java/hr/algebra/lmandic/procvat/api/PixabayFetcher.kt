package hr.algebra.lmandic.procvat.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.lmandic.procvat.ARTIKLI_PROVIDER_CONTENT_URI
import hr.algebra.lmandic.procvat.framework.downloadPicture
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixabayFetcher(private val context: Context) {

    private var pixabayApi: PixabayApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pixabayApi = retrofit.create(PixabayApi::class.java)
    }

    fun searchImage(id: Int, query: String) {
        val request = pixabayApi.searchImage(query)

        request.enqueue(object: Callback<List<PixabayItem>>{
            override fun onResponse(
                call: Call<List<PixabayItem>>,
                response: Response<List<PixabayItem>>
            ) {
                if (response.body() != null){
                    saveImageToAppFolder(id, response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<PixabayItem>>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun saveImageToAppFolder(id: Int, items: List<PixabayItem>) {

        val url = items.first().hits.first().webformatURL
        val filename = items.first().hits.first().tags.split(" ").first()

        GlobalScope.launch {
            items.forEach{
                val picturePath = downloadPicture(context, url, filename)
                val values = ContentValues().apply {
                    put(Artikl::picturePath.name, picturePath)
                }

                context.contentResolver.update(
                    ARTIKLI_PROVIDER_CONTENT_URI,
                    values,
                    "${Artikl::_id.name} == ?",
                    arrayOf(id.toString())
                )
            }

        }

    }
}