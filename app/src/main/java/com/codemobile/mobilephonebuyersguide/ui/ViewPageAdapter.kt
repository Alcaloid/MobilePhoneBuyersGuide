package com.codemobile.mobilephonebuyersguide.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.MobileListFragment

class ViewPageAdapter(
    val fragment: FragmentManager
): FragmentPagerAdapter(fragment){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MobileListFragment()
            }
            1 -> FavouriteFragment()
            else -> {
                return MobileListFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

}