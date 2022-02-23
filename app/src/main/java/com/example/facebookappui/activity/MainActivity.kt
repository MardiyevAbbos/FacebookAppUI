package com.example.facebookappui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookappui.R
import com.example.facebookappui.adapter.FeedAdapter
import com.example.facebookappui.model.Feed
import com.example.facebookappui.model.Link
import com.example.facebookappui.model.Post
import com.example.facebookappui.model.Story

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter

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
        adapter = FeedAdapter(this, feeds)
        recyclerView.adapter = adapter
    }

    fun openCreatePostActivity() {
        val intent = Intent(this, CreatePostActivity::class.java)
        launcher.launch(intent)
    }

    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> {
            if (it.resultCode == 10) {
                val link = it.data!!.getSerializableExtra("linkResult") as Link
                adapter.addItem(link)
            }
        }
    )


    private fun prepareFeedList(): ArrayList<Feed> {
        var feeds: ArrayList<Feed> = ArrayList()
        // Head
        feeds.add(Feed())
        // Story
        feeds.add(Feed(getAllStories()))
        // Post
        feeds.add(Feed(Post(R.drawable.my_photo, "", "Abbos Mardiyev", R.drawable.yangi_yil)))
        feeds.add(Feed(Link(R.drawable.elyor, "Mamayev Elyor", "https://youtu.be/oz3uGdi3f8Q", "", "", "")))
        feeds.add(
            Feed(
                Post(
                    0,
                    "https://images.unsplash.com/photo-1503249023995-51b0f3778ccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
                    "Alimov Doniyor",
                    0
                )
            )
        )
        feeds.add(
            Feed(
                Post(
                    R.drawable.shaxriyor,
                    "",
                    "Mirzamurodov Shaxriyor ",
                    R.drawable.friends
                )
            )
        )
        feeds.add(Feed(Post(R.drawable.my_photo, "", "Mardiyev Abbos", 0, getManyPostList())))
        feeds.add(Feed(Post(R.drawable.baxtiyor, "", "Maxanov Baxtiyor", R.drawable.azamat)))
        feeds.add(
            Feed(
                Post(
                    0,
                    "https://images.unsplash.com/photo-1589571894960-20bbe2828d0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzl8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
                    "Yelena Gomez",
                    0
                )
            )
        )
        feeds.add(Feed(Post(R.drawable.my_photo, "", "Mardiyev Abbos", R.drawable.yangi_yil)))
        feeds.add(
            Feed(
                Post(
                    R.drawable.shaxriyor,
                    "",
                    "Mirzamurodov Shaxriyor ",
                    0,
                    getManyPostList()
                )
            )
        )
        feeds.add(Feed(Post(R.drawable.baxtiyor, "", "Maxanov Baxtiyor", R.drawable.azamat)))
        feeds.add(
            Feed(
                Post(
                    0,
                    "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTF8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
                    "Jastin Biber",
                    0
                )
            )
        )
        feeds.add(Feed(Post(R.drawable.elyor, "", "Mamayev Elyor", R.drawable.elyor)))

        return feeds
    }

    private fun getAllStories(): ArrayList<Story> {
        val stories: ArrayList<Story> = ArrayList()
        stories.add(Story(R.drawable.elyor, "Mamayev Elyor"))
        stories.add(Story(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor"))
        stories.add(Story(R.drawable.baxtiyor, "Maxanov Baxtiyor"))
        stories.add(Story(R.drawable.shaxriyor, "Mirzamurodov Shaxriyor"))
        stories.add(Story(R.drawable.elyor, "Mamayev Elyor"))


        return stories
    }

    private fun getManyPostList(): ArrayList<String> {
        val posts: ArrayList<String> = ArrayList()
        posts.add("https://images.unsplash.com/photo-1472214103451-9374bd1c798e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8bmF0dXJlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        posts.add("https://media.istockphoto.com/photos/dubai-sky-line-with-traffic-junction-and-burj-khalifa-picture-id469692894?b=1&k=20&m=469692894&s=170667a&w=0&h=ULKQKJVKZTcn63RJ0Flipmk32eAEQRHaSqWSIa2iGlY=")
        posts.add("https://images.unsplash.com/photo-1414609245224-afa02bfb3fda?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fG5hdHVyZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")
        posts.add("https://images.unsplash.com/photo-1573435567032-ff5982925350?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8Y2F0JTIwYW5kJTIwZG9nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60")
        posts.add("https://images.unsplash.com/photo-1545591796-e2936bb2bca3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8YmxhY2slMjBob3JzZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60")

        return posts
    }


}