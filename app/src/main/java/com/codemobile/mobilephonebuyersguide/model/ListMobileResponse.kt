package com.codemobile.mobilephonebuyersguide.model

import java.io.Serializable

data class MobileListResponse(
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String,
    val fav:Boolean
) : Serializable