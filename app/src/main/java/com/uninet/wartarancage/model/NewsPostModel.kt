package com.uninet.wartarancage.model

class NewsPostModel {
    var img : Int = 0
    var title : String = ""
    var content : String = ""
    var author : String? = ""
    var date : String = ""

    constructor(img: Int, title: String, content: String, author: String?, date: String) {
        this.img = img
        this.title = title
        this.content = content
        this.author = author
        this.date = date
    }
}