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
    }

    interface MobileListPresentor {
        fun feedMobileList()
        fun setImageTarget(context: Context,target:ImageView,url:String)
        fun gotoDetailPage(context: Context,infomation:MobileListResponse)
        fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>, sortForm: String)
        fun getCurrentFav(mobileArrayList: ArrayList<MobileListResponse>, list: ArrayList<MobileListResponse>?)
        fun checkFileExist(context: Context,filename:String):Boolean
        fun addFavoriteMobile(target:MobileListResponse)
        fun removeFavoriteMobile(target:MobileListResponse?)
        fun getFavoriteMobile(): ArrayList<MobileListResponse>
    }
}