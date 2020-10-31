package co.specialforce.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FsRetrofitGenerator {
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
            .baseUrl("http://openapi.foodsafetykorea.go.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun create(): FsRetrofitService = retrofit.create(FsRetrofitService::class.java)
}