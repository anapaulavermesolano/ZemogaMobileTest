package com.example.zemogamobiletest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zemogamobiletest.R
import com.example.zemogamobiletest.databinding.ActivityDetailBinding
import com.example.zemogamobiletest.di.Injection
import com.example.zemogamobiletest.model.Comments
import com.example.zemogamobiletest.model.Post
import com.example.zemogamobiletest.viewmodel.CommentsViewModel
import com.example.zemogamobiletest.viewmodel.CommentsViewState

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var post: Post
    private val viewModel by viewModels<CommentsViewModel> {
        Injection.provideCommentsViewModelFactory()
    }
    private val commentsAdapter by lazy {
        CommentsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        with(intent){
            post = getSerializableExtra(SERIALIZABLE_EXTRA) as Post
        }

        initializeViewModel()
        initialize()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_star -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initializeViewModel(){
        viewModel.viewState.observe(this, Observer(::loadComments))
        viewModel.loadComments(postId = post.id)
    }

    private fun loadComments(viewState: CommentsViewState) {
        when(viewState) {
            is CommentsViewState.Loading -> showLoading(viewState.isViewLoading)
            is CommentsViewState.Error -> showError()
            is CommentsViewState.FilterComments -> loadingComments(viewState.comments.orEmpty())
            is CommentsViewState.EmptyPosts -> emptyComments()
        }
    }

    private fun initialize() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
            contentDetail.description.text = post.description
            contentDetail.name.text = post.user.name
            contentDetail.email.text = post.user.email
            contentDetail.phone.text = post.user.phone
        }
    }

    private fun loadingComments(comments: List<Comments>) {
        if (comments.isEmpty()){
            binding.contentDetail.comments.visibility = View.GONE
        }
        binding.contentDetail.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsAdapter
            commentsAdapter.submitList(comments)
        }
    }

    private fun showError() {
        binding.contentDetail.root.visibility = View.GONE
    }

    private fun emptyComments() {
        binding.contentDetail.comments.visibility = View.GONE
    }

    private fun showLoading(isLoading: Boolean){
        binding.apply {
            layoutLoading.root.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    companion object {
        const val SERIALIZABLE_EXTRA = "POST"
    }
}

