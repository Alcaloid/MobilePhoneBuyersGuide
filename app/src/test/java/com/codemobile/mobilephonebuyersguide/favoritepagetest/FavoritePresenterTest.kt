package com.codemobile.mobilephonebuyersguide.favoritepagetest

import com.codemobile.mobilephonebuyersguide.TestUtil
import com.codemobile.mobilephonebuyersguide.app.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.app.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.app.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.action.fragment.favorite.FavoriteContract
import com.codemobile.mobilephonebuyersguide.action.fragment.favorite.FavoritePresentation
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import junit.framework.Assert.assertEquals
import mockit.Deencapsulation
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class FavoritePresenterTest {
    @Mock
    private lateinit var view: FavoriteContract.FavoriteView
    @Mock
    private lateinit var presenter: FavoriteContract.FavoritePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter =
            FavoritePresentation(view)
    }

    @Test
    fun `sort data with Price Low To High`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_LOWTOHIGH)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(5.1,result[0].price)
        assertEquals(10.5,result[1].price)
        assertEquals(15.0,result[2].price)

    }

    @Test
    fun `sort data with Price High To Low`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_HIGHTOLOW)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(15.0,result[0].price)
        assertEquals(10.5,result[1].price)
        assertEquals(5.1,result[2].price)

    }

    @Test
    fun `sort data with Rating`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.sortMobile(RATE_5_1)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(8.1,result[0].rating)
        assertEquals(4.0,result[1].rating)
        assertEquals(3.4,result[2].rating)

    }

    @Test
    fun `Test set favorite`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))

        //when
        presenter.setMobileFav(mockMobileArray)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")

        //then
        assertEquals(1,result[0].id)
        assertEquals(2,result[1].id)
        assertEquals(3,result[2].id)
    }

    @Test
    fun `Test get favorite`() {
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        val result = presenter.getMobileFavorite()

        //then
        assertEquals(1,result[0].id)
        assertEquals(2,result[1].id)
        assertEquals(3,result[2].id)
    }

    @Test
    fun `Test remove mobile favorite`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        Deencapsulation.setField(presenter,"favoriteMobile", mockMobileArray)

        //when
        presenter.removeMobileFav(0)

        //then
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"favoriteMobile")
        assertEquals(2,result[0].id)
        assertEquals(2,result.size)
    }

}