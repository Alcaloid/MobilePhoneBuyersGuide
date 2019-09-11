package com.codemobile.mobilephonebuyersguide.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.codemobile.mobilephonebuyersguide.TestUtil
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {
    private lateinit var favoriteDAO: FavoriteDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        favoriteDAO = db.favoriteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val favoriteMobile: DatabaseEntity = TestUtil.createMobileDataBase(1,300.0,10.0)
        favoriteDAO.addFavorite(favoriteMobile)
        val idFavorite = favoriteDAO.queryFavorites()
        assertThat(idFavorite.get(0),equalTo(favoriteMobile))
    }
}