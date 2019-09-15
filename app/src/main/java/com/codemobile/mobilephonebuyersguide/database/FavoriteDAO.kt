package com.codemobile.mobilephonebuyersguide.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse

@Dao
interface FavoriteDAO {
    @Query(value = "select * from favorite")
    fun queryFavorites(): List<MobileListResponse>

    @Insert
    fun addFavorite(favoriteEntity: MobileListResponse)

    @Update
    fun updateFavorite(favoriteEntity: MobileListResponse)

    @Delete
    fun deleteFavorite(favoriteEntity: MobileListResponse)
}