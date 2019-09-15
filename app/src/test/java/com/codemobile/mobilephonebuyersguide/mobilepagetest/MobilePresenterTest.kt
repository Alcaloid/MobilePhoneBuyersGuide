package com.codemobile.mobilephonebuyersguide.mobilepagetest

import com.codemobile.mobilephonebuyersguide.TestUtil
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_HIGHTOLOW
import com.codemobile.mobilephonebuyersguide.constantclass.PRICE_LOWTOHIGH
import com.codemobile.mobilephonebuyersguide.constantclass.RATE_5_1
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListContract
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListPresentation
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import junit.framework.Assert
import mockit.Deencapsulation
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MobilePresenterTest {
    @Mock
    private lateinit var view: MobileListContract.MobileListView
    @Mock
    private lateinit var presenter: MobileListContract.MobileListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MobileListPresentation(view)
    }

    @Test
    fun `sort data with Price Low To High`() {
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, 15.0, 4.0))
        mockMobileArray.add(TestUtil.createMobile(2, 10.5, 8.1))
        mockMobileArray.add(TestUtil.createMobile(3, 5.1, 3.4))
        Deencapsulation.setField(presenter, "mobileArrayList", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_LOWTOHIGH)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "mobileArrayList")

        //then
        Assert.assertEquals(5.1, result[0].price)
        Assert.assertEquals(10.5, result[1].price)
        Assert.assertEquals(15.0, result[2].price)

    }

    @Test
    fun `sort data with Price High To Low`() {
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, 15.0, 4.0))
        mockMobileArray.add(TestUtil.createMobile(2, 10.5, 8.1))
        mockMobileArray.add(TestUtil.createMobile(3, 5.1, 3.4))
        Deencapsulation.setField(presenter, "mobileArrayList", mockMobileArray)

        //when
        presenter.sortMobile(PRICE_HIGHTOLOW)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "mobileArrayList")

        //then
        Assert.assertEquals(15.0, result[0].price)
        Assert.assertEquals(10.5, result[1].price)
        Assert.assertEquals(5.1, result[2].price)

    }

    @Test
    fun `sort data with Rating`() {
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, 15.0, 4.0))
        mockMobileArray.add(TestUtil.createMobile(2, 10.5, 8.1))
        mockMobileArray.add(TestUtil.createMobile(3, 5.1, 3.4))
        Deencapsulation.setField(presenter, "mobileArrayList", mockMobileArray)

        //when
        presenter.sortMobile(RATE_5_1)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "mobileArrayList")

        //then
        Assert.assertEquals(8.1, result[0].rating)
        Assert.assertEquals(4.0, result[1].rating)
        Assert.assertEquals(3.4, result[2].rating)

    }

    @Test
    fun `set Image Favorite`() {
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, 15.0, 4.0, favorite = false))
        mockMobileArray.add(TestUtil.createMobile(2, 10.5, 8.1, favorite =false))
        mockMobileArray.add(TestUtil.createMobile(3, 5.1, 3.4, favorite =false))
        Deencapsulation.setField(presenter, "mobileArrayList", mockMobileArray)
        val favoriteArray: ArrayList<MobileListResponse> = arrayListOf()
        favoriteArray.add(TestUtil.createMobile(1, 15.0, 4.0, favorite =true))

        //when
        presenter.getCurrentFav(favoriteArray)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "mobileArrayList")

        //then
        Assert.assertEquals(true, result[0].fav)
    }

    @Test
    fun `add favorite mobile`() {
        //given
        val mockMobile: MobileListResponse = TestUtil.createMobile(1)

        //when
        presenter.addFavoriteMobile(mockMobile)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "favMobileArrayList")

        //then
        Assert.assertEquals(1, result[0].id)
    }

    @Test
    fun `remove favorite mobile`() {
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, favorite = true))
        mockMobileArray.add(TestUtil.createMobile(2, favorite = true))
        mockMobileArray.add(TestUtil.createMobile(3, favorite = true))
        Deencapsulation.setField(presenter, "favMobileArrayList", mockMobileArray)
        val targetMobile: MobileListResponse = TestUtil.createMobile(2,favorite = true)

        //when
        presenter.removeFavoriteMobile(targetMobile)
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter, "favMobileArrayList")

        //then
        Assert.assertEquals(3, result[1].id)
    }

    @Test
    fun `get mobile favorite`(){
        //given
        val mockMobileArray: ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1, favorite = true))
        mockMobileArray.add(TestUtil.createMobile(2, favorite = true))
        mockMobileArray.add(TestUtil.createMobile(3, favorite = true))
        Deencapsulation.setField(presenter, "favMobileArrayList", mockMobileArray)

        //when
        val result = presenter.getFavoriteMobile()

        //then
        Assert.assertEquals(1, result[0].id)
        Assert.assertEquals(2, result[1].id)
        Assert.assertEquals(3, result[2].id)
    }
}