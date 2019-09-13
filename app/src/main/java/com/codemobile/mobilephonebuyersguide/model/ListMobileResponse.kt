package com.codemobile.mobilephonebuyersguide.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
data class MobileListResponse(
    @PrimaryKey val id: Int,
    @NonNull var name: String,
    @NonNull val description: String,
    @NonNull val brand: String,
    val price: Double,
    val rating: Double,
    val thumbImageURL: String,
    var fav: Boolean = false
) : Serializable