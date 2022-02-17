package com.example.facebookappui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookappui.R
import com.example.facebookappui.model.Feed
import com.example.facebookappui.model.Post
import com.google.android.material.imageview.ShapeableImageView

class FeedAdapter(var context: Context, var feeds: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2

    override fun getItemViewType(position: Int): Int {
        var feed = feeds[position]

        if (feed.isHeader) {
            return TYPE_ITEM_HEAD
        } else if (feed.stories.size > 0) {
            return TYPE_ITEM_STORY
        }
        return TYPE_ITEM_POST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_feed_head, parent, false)
            return HeadViewHolder(view)
        } else if (viewType == TYPE_ITEM_STORY) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_feed_story, parent, false)
            return StoryViewHolder(view)
        }
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoryViewHolder){
            holder.bind(feeds[position])
        }
        if (holder is PostViewHolder){
            holder.bind(feeds[position])
        }
    }

    override fun getItemCount(): Int {
        return feeds.size
    }


    inner class HeadViewHolder(view: View) : RecyclerView.ViewHolder(view)


    inner class StoryViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        fun bind(feed: Feed) {
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val adapter = StoryAdapter(context, feed.stories)
            recyclerView.adapter = adapter
        }
    }

    inner class PostViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private val ivProfile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)
        private val fullName: TextView = view.findViewById(R.id.tv_fullName)

        fun bind(feed: Feed) {
            val post: Post? = feed.post
            if (post != null) {
                ivProfile.setImageResource(post.profile)
                ivPhoto.setImageResource(post.photo)
                fullName.text = post.fullName
            }
        }
    }

}