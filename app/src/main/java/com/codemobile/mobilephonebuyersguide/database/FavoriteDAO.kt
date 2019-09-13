package com.codemobile.mobilephonebuyersguide.database

import androidx.room.*
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