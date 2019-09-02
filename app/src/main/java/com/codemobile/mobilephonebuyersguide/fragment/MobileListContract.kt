package com.codemobile.mobilephonebuyersguide.fragment

import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface MobileListContract {
    interface View{
        fun showMobileList(mobileList:List<MobileListResponse>)
    }
    interface Presentor{
        fun feedMobile()
    }
}