package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import android.content.Context
import android.widget.ImageView
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface MobileListContract {
    interface MobileListView {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage()
        fun showMobileList(list: ArrayList<MobileListResponse>)
        fun setPreFavorite()
        fun closeRefresh()
    }

    interface MobileListPresentor {
        fun feedMobileList()
        fun setImageTarget(context: Context, target: ImageView, url: String)
        fun gotoDetailPage(context: Context, infomation: MobileListResponse)
        fun sortMobile(sortForm: String)
        fun getCurrentFav(list: ArrayList<MobileListResponse>?)
        fun addFavoriteMobile(target: MobileListResponse)
        fun removeFavoriteMobile(target: MobileListResponse)
        fun getFavoriteMobile(): ArrayList<MobileListResponse>
        fun setupDatabase(context: Context)
        fun checkPreviousFavorite()

    }
}