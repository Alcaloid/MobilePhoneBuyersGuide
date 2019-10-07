package com.codemobile.mobilephonebuyersguide

import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse

class TestUtil {
    companion object {
        fun createMobile(
            id: Int,
            price: Double = 0.0,
            rating: Double = 0.0,
            name: String = "",
            brand: String = "",
            description: String = "",
            url: String = "",
            favorite: Boolean = false
        ): MobileListResponse {
            return MobileListResponse(
                id,
                description,
                brand,
                name,
                price,
                rating,
                url,
                favorite
            )
        }

        fun createImageMobile(id: Int, mobile_id: Int = 0, url: String = ""): ImageResponse {
            return ImageResponse(
                id, mobile_id, url
            )
        }
    }
}