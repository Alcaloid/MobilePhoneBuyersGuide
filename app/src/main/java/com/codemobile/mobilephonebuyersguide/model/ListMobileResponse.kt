package com.codemobile.mobilephonebuyersguide.model

data class MobileListResponse(
    val brand: String,
    val description: String,
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String
)