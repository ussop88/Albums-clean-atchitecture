package com.albums.cleanarchitecture.store.networkadapter

import com.albums.cleanarchitecture.store.data.NetworkError
import com.albums.cleanarchitecture.store.data.Model
import com.albums.store.HttpRequest
import com.albums.store.IModel
import com.albums.cleanarchitecture.store.data.NetworkServiceError
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.Request
import com.google.gson.Gson


class OkHttpAdapter : NetworkService {

    private val mOkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()

    override fun request(
        request: HttpRequest,
        model: IModel,
        completionSuccess: (List<IModel>) -> Unit,
        completionFailure: (NetworkServiceError) -> Unit
    ) {
        run(request.url, completionSuccess, completionFailure)
    }

    private fun run(
        url: String,
        completionSuccess: (List<IModel>) -> Unit,
        completionFailure: (NetworkServiceError) -> Unit
    ) {
        val request = Request.Builder()
            .url(url)
            .build()

        Thread(Runnable {
            try {
                val responseBody = mOkHttpClient.newCall(request).execute().body()
                responseBody?.let {
                    val entity = Gson().fromJson(it.string(), Array<Model>::class.java)
                    completionSuccess(entity.toList())
                }
            } catch (exception: Exception) {
                completionFailure.invoke(NetworkError(data = exception.message.toString()))
            }

        }).start()


    }
}
