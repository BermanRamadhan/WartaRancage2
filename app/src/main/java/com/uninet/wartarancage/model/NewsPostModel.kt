package com.uninet.wartarancage.model

import com.google.gson.annotations.SerializedName

class NewsPostModel {
    @SerializedName("img")
    var img : Int = 0
    @SerializedName("title")
    var title : String = ""
    @SerializedName("content")
    var content : String = ""
    @SerializedName("author")
    var author : String? = ""
    @SerializedName("date")
    var date : String = ""

    constructor(img: Int, title: String, content: String, author: String?, date: String) {
        this.img = img
        this.title = title
        this.content = content
        this.author = author
        this.date = date
    }
}