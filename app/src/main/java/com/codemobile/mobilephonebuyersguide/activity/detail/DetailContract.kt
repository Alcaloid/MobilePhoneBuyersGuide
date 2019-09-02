package com.codemobile.mobilephonebuyersguide.activity.detail

import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

interface DetailContract {
    interface DetailView {
        fun setName(name: String)
        fun setBrand(brand: String)
        fun setPrice(price: String)
        fun setRate(rate: String)
        fun setDescription(description: String)
        fun showImageMobileList(imageList: ArrayList<ImageResponse>)
    }

    interface DetailPresenttaion {
        fun getPassData(info: MobileListResponse)
        fun feedImageDetail(id: Int)
    }
}