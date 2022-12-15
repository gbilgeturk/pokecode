package com.gbilgeturk.pokemonapp.service

import android.util.Log
import com.gbilgeturk.pokemonapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {
    private val headerMap = mutableMapOf<String, String>()
    private const val connectTimeout: Long = 60
    private const val readTimeout: Long = 60

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)

        addHeaderInterceptor(okHttpClientBuilder)
        addLoggingInterceptor(okHttpClientBuilder)
        return okHttpClientBuilder.build()
    }

    private fun addHeaderInterceptor(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.addInterceptor {
            val original = it.request()

            val headerMap = setHeaders(original.url.toString(), original.body)
            val requestBuilder = original.newBuilder()
            headerMap.forEach { item ->
                requestBuilder.header(item.key, item.value)
            }
            val request = requestBuilder.method(original.method, original.body).build()
            if (BuildConfig.DEBUG) {
                Log.d("okhttp.OkHttpClient", "headers: ${request.headers}")
            }
            return@addInterceptor it.proceed(request)
        }
    }

    fun setHeaders(url: String, body: RequestBody? = null): MutableMap<String, String> {
        this.headerMap.clear()
        val headerMap = mutableMapOf<String, String>()
        headerMap["Content-Type"] = "application/json"
        headerMap["Accept"] = "application/json"

        this.headerMap.putAll(headerMap)
        return headerMap
    }




    private fun addLoggingInterceptor(okHttpClientBuilder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
    }


}