package com.example.zemogamobiletest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zemogamobiletest.databinding.ItemCommentsBinding
import com.example.zemogamobiletest.model.Comments

class CommentsAdapter: ListAdapter<Comments, CommentViewHolder>(CommentsDiffCallback()) {

    private var comments: List<Comments> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentsBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class CommentViewHolder(binding: ItemCommentsBinding) : RecyclerView.ViewHolder(binding.root) {
    private val description = binding.commentDescription
    fun bind(comment: Comments) = with(itemView) {
        description.text = comment.body
    }
}

class CommentsDiffCallback: DiffUtil.ItemCallback<Comments>() {
    override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
        return oldItem == newItem
    }
}