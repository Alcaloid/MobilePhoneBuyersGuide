package com.codemobile.mobilephonebuyersguide.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

@Entity(tableName = "favorite")
data class DatabaseEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int?
//    @NonNull var favMobile: MobileListResponse
//    @ColumnInfo(name = "user_id") val userId: String,
    @PrimaryKey val id: Int,
    @NonNull var name: String,
    @NonNull val description: String,
    @NonNull val brand:String,
    val price:Double,
    val rating:Double,
    val thumbImageURL:String,
    var fav:Boolean

)