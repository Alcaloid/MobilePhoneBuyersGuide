package com.codemobile.mobilephonebuyersguide.app.activity.detail

import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse

interface DetailContract {
    interface DetailView {
        fun setName(name: String)
        fun setBrand(brand: String)
        fun setPrice(price: String)
        fun setRate(rate: String)
        fun setDescription(description: String)
        fun showErrorMessage()
        fun showImageMobileList(imageList: List<ImageResponse>)
    }

    interface DetailPresentation {
        fun getPassData(info: MobileListResponse)
        fun feedImageDetail(id: Int)
    }
}