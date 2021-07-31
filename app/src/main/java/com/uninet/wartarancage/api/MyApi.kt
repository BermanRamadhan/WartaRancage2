package com.uninet.wartarancage.api
import com.uninet.wartarancage.model.UserModel
import retrofit2.Call
import retrofit2.http.POST

public interface MyApi {

    @POST("o")
    fun getUser(): Call<List<RegisterUser>>


}