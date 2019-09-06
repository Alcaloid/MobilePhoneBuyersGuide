package com.codemobile.mobilephonebuyersguide.favoritepagetest

import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavoriteContract
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavoritePresentation
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class FavoritePresenterTest {
    @Mock
    private lateinit var view: FavoriteContract.favView
    @Mock
    private lateinit var presenter: FavoriteContract.favPresentor
    @Mock
    private lateinit var mockArrayList: ArrayList<MobileListResponse>
    @InjectMocks
    lateinit var favoriteArrayList: ArrayList<MobileListResponse>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        view = FavouriteFragment()
        presenter = FavoritePresentation(view)
    }


//    @Test
//    fun `favorite array will return null`() {
//        presenter.setMobileFav(null)
//        mockArrayList = presenter.getMobileFavorite()
//        assertNull(mockArrayList.isNullOrEmpty())
////        assertNull(presenter.getMobileFavorite())
////        verify(mockArrayList).isEmpty()
//    }


    @Test
    fun `get favorite`() {
//        view = FavouriteFragment()
//        presenter = FavoritePresentation(view)
        val result = arrayListOf(
            MobileListResponse(
                "xxx",
                "xxx",
                0,
                "yyy",
                10.0,
                10.0,
                "rrrr",
                true
            )
        )
        presenter.setMobileFav(result)
        `when`(presenter.getMobileFavorite()).thenReturn(result)
    }
}