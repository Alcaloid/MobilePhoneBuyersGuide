package com.codemobile.mobilephonebuyersguide.database

import androidx.room.*

@Dao
interface FavoriteDAO{
    @Query( value = "select * from favorite")
    fun queryFavorites():DatabaseEntity
    @Insert
    fun addFavorite(favoriteEntity: DatabaseEntity)
    @Update
    fun updateFavorite(favoriteEntity: DatabaseEntity)
    @Delete
    fun deleteFavorite(favoriteEntity: DatabaseEntity)
}