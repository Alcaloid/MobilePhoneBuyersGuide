package com.codemobile.mobilephonebuyersguide.favoritepagetest

import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavoriteContract
import com.codemobile.mobilephonebuyersguide.fragment.favorite.FavoritePresentation
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import junit.framework.Assert.assertEquals
import mockit.Deencapsulation
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class FavoritePresenterTest {
    @Mock
    private lateinit var view: FavoriteContract.favView
    @Mock
    private lateinit var presenter: FavoriteContract.favPresentor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoritePresentation(view)
    }

    fun addMobile(id:Int,price:Double,rating:Double):MobileListResponse{
        val result = MobileListResponse(
            "xxx",
            "xxx",
            id,
            "yyy",
            price,
            rating,
            "rrrr",
            true
        )
        return result
    }

    @Test
    fun `sort data with Price Low To High`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_LOWTOHIGH)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(result[0].price,5.1)
        assertEquals(result[1].price,10.5)
        assertEquals(result[2].price,15.0)

    }

    @Test
    fun `sort data with Price High To Low`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_HIGHTOLOW)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(result[0].price,15.0)
        assertEquals(result[1].price,10.5)
        assertEquals(result[2].price,5.1)

    }

    @Test
    fun `sort data with Rating`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(RATE_5_1)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(result[0].rating,8.1)
        assertEquals(result[1].rating,4.0)
        assertEquals(result[2].rating,3.4)

    }

    @Test
    fun `Test set favorite`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))

        //when
        presenter.setMobileFav(mockMobileArray)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(result[0].id,1)
        assertEquals(result[1].id,2)
        assertEquals(result[2].id,3)
    }

    @Test
    fun `Test get favorite`() {
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        val result = presenter.getMobileFavorite()

        //then
        assertEquals(result[0].id,1)
        assertEquals(result[1].id,2)
        assertEquals(result[2].id,3)
    }

    @Test
    fun `Test remove mobile favorite`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(addMobile(1,15.0,4.0))
        mockMobileArray.add(addMobile(2,10.5,8.1))
        mockMobileArray.add(addMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.removeMobileFav(0)

        //then
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")
        assertEquals(result[0].id,2)
        assertEquals(result.size,2)
    }

}