package com.example.aplicativo2.api.entity

import com.google.gson.annotations.SerializedName

class DogCuriosityEntity {

    @SerializedName("facts")
    lateinit var facts: List<String>
}