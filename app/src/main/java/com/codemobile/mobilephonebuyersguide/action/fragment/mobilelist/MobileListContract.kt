package com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist

import android.content.Context
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse

interface MobileListContract {
    interface MobileListView {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage()
        fun showMobileList(list: ArrayList<MobileListResponse>)
        fun setPreFavorite()
        fun closeRefresh()
    }

    interface MobileListPresenter {
        fun feedMobileList()
        fun sortMobile(sortForm: String)
        fun getCurrentFav(list: ArrayList<MobileListResponse>?)
        fun addFavoriteMobile(target: MobileListResponse)
        fun removeFavoriteMobile(target: MobileListResponse)
        fun getFavoriteMobile(): ArrayList<MobileListResponse>
        fun setupDatabase(context: Context)
        fun checkPreviousFavorite()
        fun makeFavoriteMobileInRoomDatabase(target: MobileListResponse, roomFunction:String)
    }
}