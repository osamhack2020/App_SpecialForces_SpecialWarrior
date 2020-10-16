package co.specialforce.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitGenerator {
    private val builder = OkHttpClient.Builder()
    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        builder.addInterceptor(httpLoggingInterceptor)
    }
    private val okHttpClient = builder.build()

    private val retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://spefor.ml")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun create(): RetrofitService = retrofit.create(RetrofitService::class.java)
}