package com.example.facebookappui.model

import java.io.Serializable

data class Link(
    var profile: Int,
    var fullName: String,
    var link: String,
    var imageUrl: String,
    var linkName: String,
    var linkTitle: String
) : Serializable
