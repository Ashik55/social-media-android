package com.meta.socialmedia.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.meta.socialmedia.R
import com.meta.socialmedia.data.Post

class PostAdapter(
    private val postList: List<Post>,
    private val onItemClick: (Post) -> Unit
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    val TYPE_IMAGE = 1
    val TYPE_TEXT = 2


    override fun getItemViewType(position: Int): Int {
        return when (postList[position].type) {
            "image" -> TYPE_IMAGE
            "text" -> TYPE_TEXT
            else -> TYPE_TEXT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            TYPE_IMAGE -> layoutInflater.inflate(R.layout.post_image, parent, false)
            TYPE_TEXT -> layoutInflater.inflate(R.layout.post_text, parent, false)
            else -> layoutInflater.inflate(R.layout.post_text, parent, false)
        }
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
        holder.itemView.setOnClickListener { onItemClick(post) }
    }

    override fun getItemCount() = postList.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            // Bind data based on view type
            when (getItemViewType(adapterPosition)) {
                TYPE_IMAGE -> {
                    val userImage = itemView.findViewById<ImageView>(R.id.userImage)
                    userImage.load(post.user?.image) {
                        crossfade(true)
                    }
                    val name = itemView.findViewById<TextView>(R.id.userName)
                    name.text = post.user?.name

                    val bio = itemView.findViewById<TextView>(R.id.userBio)
                    bio.text = post.user?.bio

                    val imageView = itemView.findViewById<ImageView>(R.id.image)
                    imageView.load(post.imageURL) {
                        crossfade(true)
                    }

                    val title = itemView.findViewById<TextView>(R.id.post_title)
                    title.text = post.title
                    itemView.findViewById<TextView>(R.id.post_description).text = post.description
                }

                TYPE_TEXT -> {
                    itemView.findViewById<TextView>(R.id.post_title).text = post.title
                    itemView.findViewById<TextView>(R.id.post_description).text = post.description
                }

            }
        }
    }
}