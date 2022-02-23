package com.example.facebookappui.model

class Feed {

    var isHeader: Boolean = false
    var post: Post? = null
    var link: Link? = null
    var stories: ArrayList<Story> = ArrayList()

    constructor() {
        this.isHeader = true
    }

    constructor(stories: ArrayList<Story>) {
        this.stories = stories
        this.isHeader = false
    }

    constructor(link: Link) {
        this.link = link
        this.isHeader = false
    }

    constructor(post: Post) {
        this.post = post
        this.isHeader = false
    }

}