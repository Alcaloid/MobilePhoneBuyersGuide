package com.codemobile.mobilephonebuyersguide.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class DatabaseEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int?,
//    @ColumnInfo(name = "user_id") val userId: String,
    @PrimaryKey val mobile_id: Int,
    @NonNull var name: String,
    @NonNull val desciption: String,
    @NonNull val brand:String,
    val price:Double,
    val rate:Double,
    val url:String,
    var fav:Boolean

)