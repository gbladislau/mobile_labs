package com.example.aplicativo2.api.service

import com.example.aplicativo2.api.entity.CatCuriosityEntity
import retrofit2.http.GET
import retrofit2.Call


interface CatCuriosityService {
    @GET("fact")
    fun getNewCuriosity(): Call<CatCuriosityEntity>
}