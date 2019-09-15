package com.codemobile.mobilephonebuyersguide.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.codemobile.mobilephonebuyersguide.internet.BaseSortInterface
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListFragment
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

class ViewPageAdapter(
    private val fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val TAB_TITLES = arrayOf("Mobile list", "Favorites list")

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
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    fun updateSortTye(sortType: String) {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is BaseSortInterface) {
                it.updateSortType(sortType)
            }
        }
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

    fun setUnFavoriteMobile() {
        val unFavoriteMobile = getUnFavoriteMobile()
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is MobileListFragment) {
                it.checkUnFav(unFavoriteMobile)
            }
        }
    }

    private fun getFavoriteMobile(): ArrayList<MobileListResponse>? {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is MobileListFragment) {
                return it.getFavData()
            }
        }
        return null
    }

    private fun getUnFavoriteMobile(): ArrayList<MobileListResponse>? {
        val fragments = fragmentManager.fragments
        fragments.forEach {
            if (it is FavouriteFragment) {
                return it.getUnFav()
            }
        }
        return null
    }
}