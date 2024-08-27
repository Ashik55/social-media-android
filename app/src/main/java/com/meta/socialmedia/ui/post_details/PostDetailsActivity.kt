package com.meta.socialmedia.ui.post_details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.meta.socialmedia.R
import com.meta.socialmedia.data.Post

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var userName: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_post_details)

        textView = findViewById(R.id.textView)
        userName = findViewById(R.id.userName)
        imageView = findViewById(R.id.imageView)


        val post = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item", Post::class.java)
        } else {
            intent.getParcelableExtra<Post>("item")
        }
        Log.d("PostDetails", post.toString())

        post?.let {
            // Update UI with post details
            textView.text = it.title
            userName.text = it.user?.name
            imageView.load(it.imageURL) {
                crossfade(true)
            }

        }


    }
}