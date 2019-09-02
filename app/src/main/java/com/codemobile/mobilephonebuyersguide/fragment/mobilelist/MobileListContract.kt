package com.codemobile.mobilephonebuyersguide.fragment.mobilelist

import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface MobileListContract {
    interface MobileListView {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage()
        fun showMobileList(list: ArrayList<MobileListResponse>)
        fun checkUnFav(list: ArrayList<MobileListResponse>)
    }

    interface MobileListPresentor {
        fun feedMobileList()
        fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>, sortForm: String)
        fun getCurrentFav(mobileArrayList: ArrayList<MobileListResponse>, ist: ArrayList<MobileListResponse>)
    }
}