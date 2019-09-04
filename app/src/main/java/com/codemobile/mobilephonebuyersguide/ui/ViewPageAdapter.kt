package com.codemobile.mobilephonebuyersguide.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.internet.BaseSortInterface
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListFragment
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

class ViewPageAdapter(
    val fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    fun updateSortTye(sortType: String) {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is BaseSortInterface) {
                it.updateSortType(sortType)
            }
        }
    }

    fun getFavoriteMobile(): ArrayList<MobileListResponse>? {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is MobileListFragment) {
                return it.getFavData()
            }
        }
        return null
    }

    fun setFavoriteMobile() {
        val favoriteMobile = getFavoriteMobile()
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is FavouriteFragment) {
                it.sendDataFav(favoriteMobile)
            }
        }
    }

    fun getUnFavoriteMobile(): ArrayList<MobileListResponse>? {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is FavouriteFragment) {
                return it.getUnFav()
            }
        }
        return null
    }

    fun setUnFavoriteMobile() {
        val unFavoriteMobile = getUnFavoriteMobile()
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is MobileListFragment) {
                it.checkUnFav(unFavoriteMobile)
            }
        }
    }


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                return MobileListFragment()
            }
            1 -> {
                return FavouriteFragment()
            }
            else -> {
                MobileListFragment()
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
}