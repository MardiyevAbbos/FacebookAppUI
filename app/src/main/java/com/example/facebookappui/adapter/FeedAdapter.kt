package com.example.facebookappui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.GRAY
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookappui.R
import com.example.facebookappui.model.Feed
import com.example.facebookappui.model.Post
import com.google.android.material.imageview.ShapeableImageView
import android.R.id
import android.text.Html
import android.widget.LinearLayout


class FeedAdapter(var context: Context, var feeds: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_UPDATE_PROFILE = 3

    override fun getItemViewType(position: Int): Int {
        var feed = feeds[position]

        if (feed.isHeader) {
            return TYPE_ITEM_HEAD
        } else if (feed.stories.size > 0) {
            return TYPE_ITEM_STORY
        } else if (feed.post!!.profile == 0) {
            return TYPE_ITEM_UPDATE_PROFILE
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
        } else if (viewType == TYPE_ITEM_UPDATE_PROFILE) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_update_profile, parent, false)
            return UpdateProfileViewHolder(view)
        }
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoryViewHolder -> {
                holder.bind(feeds[position])
            }
            is PostViewHolder -> {
                holder.bind(feeds[position])
            }
            is UpdateProfileViewHolder -> {
                holder.bind(feeds[position])
            }
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
        private val fullName: TextView = view.findViewById(R.id.tv_fullName)
        private val ivPhoto: ShapeableImageView = view.findViewById(R.id.iv_photo)
        private val llPhotos: LinearLayout = view.findViewById(R.id.ll_posts)

        fun bind(feed: Feed) {
            val post: Post? = feed.post
            ivProfile.setImageResource(post!!.profile)
            fullName.text = post.fullName

            if (post.photo == 0) {
                ivPhoto.visibility = View.GONE
                llPhotos.visibility = View.VISIBLE
                Glide.with(context).load(post.photos?.get(0)).into(view.findViewById(R.id.iv_post_1))
                Glide.with(context).load(post.photos?.get(1)).into(view.findViewById(R.id.iv_post_2))
                Glide.with(context).load(post.photos?.get(2)).into(view.findViewById(R.id.iv_post_3))
                Glide.with(context).load(post.photos?.get(3)).into(view.findViewById(R.id.iv_post_4))
                Glide.with(context).load(post.photos?.get(4)).into(view.findViewById(R.id.iv_post_5))
            } else {
                llPhotos.visibility = View.GONE
                ivPhoto.visibility = View.VISIBLE
                ivPhoto.setImageResource(post.photo)
            }
        }
    }

    inner class UpdateProfileViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private val ivProfile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        private val ivPhoto: ShapeableImageView = view.findViewById(R.id.iv_photo)
        private val fullName: TextView = view.findViewById(R.id.tv_fullName)

        fun bind(feed: Feed) {
            val post: Post? = feed.post
            Glide.with(context).load(post!!.profileS).into(ivProfile)
            Glide.with(context).load(post.profileS).into(ivPhoto)
            val sss: String = "<b>" + post.fullName + "</b> " + " updated his profile picture."
            fullName.text = Html.fromHtml(sss)
        }
    }

}