package com.example.facebookappui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.facebookappui.R
import com.example.facebookappui.helper.Utils
import com.example.facebookappui.model.Link
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.nodes.Document
import java.net.URL

class CreatePostActivity : AppCompatActivity() {

    private lateinit var edtMind: EditText
    private lateinit var tvPost: TextView
    private lateinit var ivPhoto: ImageView
    private lateinit var tvLinkName: TextView
    private lateinit var tvLinkTitle: TextView
    private lateinit var ivRemove: ShapeableImageView
    private lateinit var llStatePost: LinearLayout
    private var isFindLink = false

    private var postPhoto: String = ""
    private var postName: String = ""
    private var postTitle: String = ""

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            if (!isFindLink && containsLink(s.toString())) {
                llStatePost.visibility = View.VISIBLE
            } else if (!isFindLink) {
                llStatePost.visibility = View.GONE
            }

            // check whether both the fields are empty or not
            if (s.toString().isEmpty() && llStatePost.visibility == View.GONE) {
                tvPost.setBackgroundResource(R.drawable.btn_shape_rounded_border_gray)
                tvPost.setTextColor(Color.GRAY)
                tvPost.isEnabled = false
            } else {
                tvPost.setBackgroundResource(R.drawable.btn_shape_rounded_border_blue)
                tvPost.setTextColor(Color.WHITE)
                tvPost.isEnabled = true
            }

        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        initViews()

    }

    private fun initViews() {
        val ivBack: ImageView = findViewById(R.id.iv_back)
        ivBack.setOnClickListener { finish() }

        edtMind = findViewById(R.id.edt_mind)
        tvPost = findViewById(R.id.tv_post)
        ivPhoto = findViewById(R.id.iv_photo)
        tvLinkName = findViewById(R.id.tv_siteName)
        tvLinkTitle = findViewById(R.id.tv_siteTitle)
        ivRemove = findViewById(R.id.iv_clear)
        llStatePost = findViewById(R.id.ll_statePost)

        ivRemove.setOnClickListener {
            llStatePost.visibility = View.GONE
        }

        if (!tvPost.isEnabled){
            tvPost.setOnClickListener{
                val link: Link = if (llStatePost.visibility == View.GONE){
                    Link(R.drawable.my_photo, "Abbos Mardiyev", edtMind.text.toString(),"","","")
                }else{
                    Link(R.drawable.my_photo, "Abbos Mardiyev", edtMind.text.toString(),postPhoto,postName, postTitle)
                }
                sendResultSerializable(link)
            }
        }

        edtMind.addTextChangedListener(textWatcher)

    }

    private fun sendResultSerializable(link: Link) {
        val intent = Intent()
        intent.putExtra("linkResult", link)
        setResult(10, intent)
        finish()
    }


    private fun getElementsJsoup(url: String) {
        Utils.getJsoupData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: Document ->
                val metaTags = result.getElementsByTag("meta")
                for (element in metaTags) {
                    when {
                        element.attr("property").equals("og:image") -> {
                            postPhoto = element.attr("content")
                            Picasso.get().load(postPhoto).into(ivPhoto)
                        }
                        element.attr("property").equals("og:description") -> {
                            val uri = URL(url)
                            postName = uri.host
                            tvLinkName.text = postName
                        }
                        element.attr("property").equals("og:title") -> {
                            postTitle = element.attr("content")
                            tvLinkTitle.text = postTitle
                        }
                    }
                }
            }
    }

    private fun containsLink(input: String): Boolean {
        val parts = input.split(" ")
        for (item in parts) {
            if (!isFindLink && Patterns.WEB_URL.matcher(item).matches()) {
                getElementsJsoup(item)
                isFindLink = true
                return true
            }
        }
        return false
    }

}