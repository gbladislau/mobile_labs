package com.example.aplicativo2.api.service

import com.example.aplicativo2.api.entity.DogCuriosityEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogCuriosityService {

    @GET("facts?number=1")
    fun getNewCuriosity(): Call <DogCuriosityEntity>

}