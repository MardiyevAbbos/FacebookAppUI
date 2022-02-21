package com.example.facebookappui.model

data class Post(
    var profile: Int,
    var profileS: String,
    var fullName: String,
    var photo: Int,
    var photos: ArrayList<String>? = null
)
