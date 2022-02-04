package com.example.zemogamobiletest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Music Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "All")
                val postFragment = PostFragment()
                postFragment.arguments = bundle
                return postFragment
            }
            else -> {
                // # Movies Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Favorites")
                val favoritesFragment = PostFragment()
                favoritesFragment.arguments = bundle
                return favoritesFragment
            }
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}