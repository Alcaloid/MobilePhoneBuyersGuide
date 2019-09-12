package com.codemobile.mobilephonebuyersguide

import com.codemobile.mobilephonebuyersguide.database.DatabaseEntity
import com.codemobile.mobilephonebuyersguide.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

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
                brand,
                description,
                id,
                name,
                price,
                rating,
                url,
                favorite
            )
        }

        fun createMobileDataBase(id: Int, price: Double, rating: Double): DatabaseEntity {
            return DatabaseEntity(
                id = id,
                price = price,
                rating = rating,
                name = "",
                description = "",
                brand = "",
                fav = true,
                thumbImageURL = ""
            )
        }

        fun createImageMobile(id: Int, mobile_id: Int = 0, url: String = ""): ImageResponse {
            return ImageResponse(
                id, mobile_id, url
            )
        }
    }
}