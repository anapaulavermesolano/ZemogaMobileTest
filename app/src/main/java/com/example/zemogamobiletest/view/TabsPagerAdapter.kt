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
                // # ALL Fragment
                val bundle = Bundle()
                bundle.putString(MainActivity.TABS_KEY, MainActivity.TAB_ALL)
                val postFragment = PostFragment()
                postFragment.arguments = bundle
                return postFragment
            }
            else -> {
                // # FAVORITES Fragment
                val bundle = Bundle()
                bundle.putString(MainActivity.TABS_KEY, MainActivity.TAB_FAVORITES)
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