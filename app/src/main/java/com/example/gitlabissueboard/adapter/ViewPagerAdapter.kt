package com.example.gitlabissueboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gitlabissueboard.fragment.AllFragment
import com.example.gitlabissueboard.fragment.ClosedFragment
import com.example.gitlabissueboard.fragment.OpenFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return OpenFragment()
            1 -> return ClosedFragment()
            else -> AllFragment()
        }
    }
}