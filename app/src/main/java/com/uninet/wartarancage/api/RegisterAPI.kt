package com.uninet.wartarancage.api

import com.squareup.okhttp.Call
import com.uninet.wartarancage.model.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("/insert.php")
    open fun daftar(@Field("npm") npm: String?,
                    @Field("nama") nama: String?,
                    @Field("kelas") kelas: String?,
                    @Field("sesi") sesi: String?)
}