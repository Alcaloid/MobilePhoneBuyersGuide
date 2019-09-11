package com.codemobile.mobilephonebuyersguide

import com.codemobile.mobilephonebuyersguide.database.DatabaseEntity
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

class TestUtil {
    companion object {
        fun createMobile(
            id: Int,
            price: Double = 0.0,
            rating: Double = 0.0,
            favorite: Boolean = false
        ): MobileListResponse {
            return MobileListResponse(
                "xxx",
                "xxx",
                id,
                "yyy",
                price,
                rating,
                "rrrr",
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
    }
}