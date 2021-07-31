package com.uninet.wartarancage.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    var BASEURL = "http://localhost:9000/"
    lateinit var retrofit : Retrofit

    fun connectRetrofit() : Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}