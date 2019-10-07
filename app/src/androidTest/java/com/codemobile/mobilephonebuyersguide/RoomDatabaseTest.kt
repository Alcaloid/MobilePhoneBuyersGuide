package com.codemobile.mobilephonebuyersguide

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.codemobile.mobilephonebuyersguide.action.database.AppDatabase
import com.codemobile.mobilephonebuyersguide.action.database.FavoriteDAO
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {
    private lateinit var favoriteDAO: FavoriteDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        favoriteDAO = db.favoriteDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val favoriteMobile = createMobile(1,300.0,10.0)
        favoriteDAO.addFavorite(favoriteMobile)
        val idFavorite = favoriteDAO.queryFavorites()
        assertThat(idFavorite[0],equalTo(favoriteMobile))
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    fun createMobile(
        id: Int,
        price: Double = 0.0,
        rating: Double = 0.0,
        name: String = "",
        brand: String = "",
        description: String = "",
        url: String = "",
        favorite: Boolean = false
    ): MobileListResponse {
        return MobileListResponse(
            id,
            description,
            brand,
            name,
            price,
            rating,
            url,
            favorite
        )
    }

}