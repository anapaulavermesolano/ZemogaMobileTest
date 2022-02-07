package com.example.zemogamobiletest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zemogamobiletest.R
import com.example.zemogamobiletest.databinding.ItemPostBinding
import com.example.zemogamobiletest.model.Post

class PostsAdapter(private var onClick: (Post) -> Unit) :
ListAdapter<Post, PostViewHolder>(SelectableDiffCallback()) {

    private var posts: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
    }

    fun allPosts(post: List<Post>) {
        this.posts = post
        submitList(
            post.sortedBy { !it.isFavorite }
        )
    }

    fun favoritesPosts(favorites: List<Post>) {
        this.posts = favorites
        submitList(
            favorites.filter { it.isFavorite }
        )
    }
}

class PostViewHolder(binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    private val title = binding.title
    private val imageView = binding.imageView
    private val content = binding.content
    fun bind(post: Post,
    callback: (Post) -> Unit) = with(itemView) {
        title.text = post.title
        if (post.isFavorite) imageView.setImageResource(R.drawable.ic_baseline_star_24)
        if (!post.isOpen) imageView.setImageResource(R.drawable.ic_baseline_brightness_1_24)
        content.setOnClickListener {
            callback.invoke(post)
        }
    }
}

class SelectableDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
         return oldItem == newItem
    }
}