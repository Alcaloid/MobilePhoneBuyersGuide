package com.codemobile.mobilephonebuyersguide.ui

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.fragment.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.MobileListFragment
import android.provider.SyncStateContract.Helpers.update
import androidx.viewpager.widget.PagerAdapter


class ViewPageAdapter(
    val fragment: FragmentManager
): FragmentPagerAdapter(fragment){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MobileListFragment()
            }
            1 -> {
                FavouriteFragment()
            }
            else -> {
                return MobileListFragment()
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
//    override fun getItemPosition(`object`: Any): Int {
//        val f = `object` as FavouriteFragment
//        if (f != null) {
//            f.update()
//        }
//        return super.getItemPosition(`object`)
//    }
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}