package com.codemobile.mobilephonebuyersguide.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.MobileListFragment

class ViewPageAdapter(
    val fragment: FragmentManager,
    val mobileFrag:MobileListFragment,
    val favFrag:FavouriteFragment
): FragmentPagerAdapter(fragment){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                return mobileFrag
            }
            1 -> {
                return favFrag
            }
            else -> {
                return mobileFrag
            }
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Mobile List"
            }
            1 -> "Favorite"
            else -> {
                return ""
            }
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

}