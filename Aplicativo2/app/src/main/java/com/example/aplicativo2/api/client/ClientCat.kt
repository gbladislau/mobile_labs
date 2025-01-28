package com.example.aplicativo2.api.client

import com.example.aplicativo2.api.service.CatCuriosityService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientCat {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://catfact.ninja/"

        private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun createCatCuriosityService( ): CatCuriosityService {
            return getClientInstance().create(CatCuriosityService::class.java)
        }
    }
}
