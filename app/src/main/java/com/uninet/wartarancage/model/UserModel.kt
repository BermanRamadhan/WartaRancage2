package com.uninet.wartarancage.model

import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("username")
    var username : String = ""
    @SerializedName("email")
    var email : String = ""
    @SerializedName("password")
    var password : String = ""
    @SerializedName("level")
    var level : String = ""
    @SerializedName("api_token")
    var api_token : String = ""
    @SerializedName("fg_active")
    var fg_active : String = ""
    @SerializedName("created_at")
    var created_at : String = ""
    @SerializedName("updated_at")
    var updated_at : String = ""


    constructor(
        username: String,
        email: String,
        password: String,
        level: String,
        api_token: String,
        fg_active: String,
        created_at: String,
        updated_at: String
    ) {
        this.username = username
        this.email = email
        this.password = password
        this.level = level
        this.api_token = api_token
        this.fg_active = fg_active
        this.created_at = created_at
        this.updated_at = updated_at
    }
}