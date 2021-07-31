package com.uninet.wartarancage.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataRepository {

    fun create(): MyApi{
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://localhost:9000/")
                .build()
        return retrofit.create(MyApi::class.java)
    }
}