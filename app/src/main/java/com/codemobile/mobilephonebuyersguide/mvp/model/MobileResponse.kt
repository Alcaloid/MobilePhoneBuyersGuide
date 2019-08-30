package com.codemobile.mobilephonebuyersguide.mvp.model

import java.io.Serializable

data class MobileResponse(
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String,
    var fav:Boolean = false
) : Serializable