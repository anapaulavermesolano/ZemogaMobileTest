package com.example.zemogamobiletest.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zemogamobiletest.databinding.FragmentPostBinding
import com.example.zemogamobiletest.model.Post


class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null

    private val binding get() = _binding!!
    private val postsAdapter by lazy {
        PostsAdapter(onClick = ::onClickPost)
    }
    private lateinit var fragmentName: String
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentName = arguments?.getString(MainActivity.TABS_KEY).orEmpty()
        if (fragmentName == MainActivity.TAB_ALL) setRecyclerPosts()
        else setRecyclerFavorites()
    }
    private fun setRecyclerPosts() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
            postsAdapter.allPosts((activity as MainActivity).allPost)
        }
    }

    private fun setRecyclerFavorites() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
            postsAdapter.favoritesPosts((activity as MainActivity).allPost)
        }
    }
    private fun onClickPost(post: Post) {
        val i = Intent(requireActivity(), DetailActivity::class.java)
        i.putExtra(DetailActivity.SERIALIZABLE_EXTRA, post)
        startActivity(i)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}