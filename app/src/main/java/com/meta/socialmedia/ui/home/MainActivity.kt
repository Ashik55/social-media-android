package com.meta.socialmedia.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meta.socialmedia.R
import com.meta.socialmedia.data.Post
import com.meta.socialmedia.data.User
import com.meta.socialmedia.ui.post_details.PostDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val list = List(50) { index ->
            Post(
                id = index + 1,
                type = if (index % 3 == 0) "image" else if (index % 3 == 1) "text" else "video",
                title = when (index % 3) {
                    0 -> "Image Post #$index"
                    1 -> "Text Post #$index"
                    else -> "Video Post #$index"
                },
                description = "Description for post #$index",
                imageURL = if (index % 3 == 0) "https://picsum.photos/200/200?random=$index" else null,
                videoURL = if (index % 3 == 2) "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4" else null,
                user = User(
                    id = index + 10,
                    name = "User $index",
                    email = "user$index@example.com",
                    bio = "Bio for user #$index",
                    image = "https://picsum.photos/100/100?random=$index"
                )
            )
        }
        recyclerView.adapter = PostAdapter(list) { post ->

            val intent = Intent(this, PostDetailsActivity::class.java)
            intent.putExtra("item", post)
            startActivity(intent)


        }
        recyclerView.addItemDecoration(ItemSpacingDecoration(horizontal = 4, vertical = 16))
        recyclerView.setPadding(0, 0, 0, 80)


//        val posts = Utils().generateDummyPosts()
//        postAdapter = PostAdapter(posts) { post ->
////            val intent = Intent(this, Details::class.java).apply {
////                putExtra("post", post)
////            }
////            startActivity(intent)
//        }

//        recyclerView.adapter = postAdapter

//        recyclerView.clipToPadding = false // Ensure that padding is applied correctly
//


    }
}