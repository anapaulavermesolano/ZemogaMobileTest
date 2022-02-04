package com.example.zemogamobiletest.view

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.lifecycle.Observer
import com.example.zemogamobiletest.R
import com.example.zemogamobiletest.databinding.ActivityMainBinding
import com.example.zemogamobiletest.di.Injection
import com.example.zemogamobiletest.model.Post
import com.example.zemogamobiletest.viewmodel.PostViewState
import com.example.zemogamobiletest.viewmodel.PostsViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pagerAdapter = TabsPagerAdapter(supportFragmentManager, lifecycle, NUMBER_OF_TABS)
    var allPost: List<Post> = emptyList()

    private val viewModel by viewModels<PostsViewModel> {
        Injection.provideViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel.viewState.observe(this, Observer(::loadAllPosts))
        viewModel.loadPosts()

        binding.apply {
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun loadAllPosts(viewState: PostViewState) {
        when(viewState) {
            is PostViewState.Loading -> showLoading(viewState.isViewLoading)
            is PostViewState.Error -> showError(viewState.onMessageError)
            is PostViewState.AllPost -> loadingPosts(viewState.post.orEmpty())
        }
    }

    private fun loadingPosts(allPost: List<Post>) {
        this.allPost = allPost
        binding.apply {
            // Set the ViewPager Adapter
            contentMain.viewPager.apply {
                adapter = pagerAdapter
            }

            // Tabs Customization
            contentMain.tabLayout.apply {
                setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                tabTextColors = ContextCompat.getColorStateList(this@MainActivity, R.color.black)
            }

            TabLayoutMediator(contentMain.tabLayout, contentMain.viewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "ALL"
                        tab.setIcon(R.drawable.ic_baseline_notifications_24)
                    }
                    1 -> {
                        tab.text = "FAVORITES"
                        tab.setIcon(R.drawable.ic_baseline_star_24)
                    }
                }
                // Change color of the icons
                tab.icon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.BLACK,
                        BlendModeCompat.SRC_ATOP
                    )
            }.attach()
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.apply {
            contentMain.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showError(onMessageError: String){
        binding.apply {
            contentMain.layoutError.root.visibility = View.VISIBLE
            contentMain.layoutError.textViewEmptyList.text = onMessageError
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.loadPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val NUMBER_OF_TABS = 2
    }
}