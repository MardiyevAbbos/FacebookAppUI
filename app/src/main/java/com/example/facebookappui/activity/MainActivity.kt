package com.example.facebookappui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookappui.R
import com.example.facebookappui.adapter.FeedAdapter
import com.example.facebookappui.model.Feed
import com.example.facebookappui.model.Post
import com.example.facebookappui.model.Story

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        refreshAdapter(prepareFeedList())
    }

    private fun refreshAdapter(feeds: ArrayList<Feed>) {
        val adapter = FeedAdapter(this, feeds)
        recyclerView.adapter = adapter
    }


    private fun prepareFeedList(): ArrayList<Feed> {
        var feeds: ArrayList<Feed> = ArrayList()
        // Head
        feeds.add(Feed())
        // Story
        feeds.add(Feed(getAllStories()))
        // Post
        feeds.add(Feed(Post(R.drawable.my_photo, "Mardiyev Abbos", R.drawable.yangi_yil)))
        feeds.add(Feed(Post(R.drawable.elyor, "Mamayev Elyor", R.drawable.elyor)))
        feeds.add(Feed(Post(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor", R.drawable.friends)))
        feeds.add(Feed(Post(R.drawable.baxtiyor, "Maxanov Baxtiyor", R.drawable.azamat)))
        feeds.add(Feed(Post(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor", R.drawable.friends)))
        feeds.add(Feed(Post(R.drawable.my_photo, "Mardiyev Abbos", R.drawable.yangi_yil)))
        feeds.add(Feed(Post(R.drawable.elyor, "Mamayev Elyor", R.drawable.elyor)))

        return feeds
    }

    private fun getAllStories(): ArrayList<Story> {
        var stories: ArrayList<Story> = ArrayList()
        stories.add(Story(R.drawable.elyor, "Mamayev Elyor"))
        stories.add(Story(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor"))
        stories.add(Story(R.drawable.baxtiyor, "Maxanov Baxtiyor"))
        stories.add(Story(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor"))
        stories.add(Story(R.drawable.elyor, "Mamayev Elyor"))


        return stories
    }


}