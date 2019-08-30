package com.codemobile.mobilephonebuyersguide.mvp.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.nonmvp.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.nonmvp.fragment.MobileListFragment

class SubViewPageAdapter (
    val fragment: FragmentManager
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