package com.codemobile.mobilephonebuyersguide.fragment

import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface MobileListContract {
    interface MobileListView{
        fun showMobileList(list:ArrayList<MobileListResponse>)
    }
    interface MobileListPresentor{
        fun feedMobileList()
        fun sortMobile(mobileArrayList: ArrayList<MobileListResponse>,sortForm:String)
    }
}