package com.example.zemogamobiletest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.zemogamobiletest.R
import com.example.zemogamobiletest.databinding.ActivityDetailBinding
import com.example.zemogamobiletest.model.Post

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        with(intent){
            post = getSerializableExtra("POST") as Post
        }

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
}

