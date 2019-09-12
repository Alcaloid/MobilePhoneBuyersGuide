package com.codemobile.mobilephonebuyersguide.api

import com.codemobile.mobilephonebuyersguide.TestUtil
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListContract
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListPresentation
import com.codemobile.mobilephonebuyersguide.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import mockit.Deencapsulation
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Callback
import retrofit2.Response

class APIUnitTesting {
    @Mock
    private lateinit var view: MobileListContract.MobileListView
    @Mock
    private lateinit var presenter: MobileListContract.MobileListPresentor
    @Mock
    private lateinit var service:ApiInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MobileListPresentation(view, service)
    }

    @Test
    fun `Test mobile feeddata Fail`(){
        //given
        val call: retrofit2.Call<List<MobileListResponse>> = mock()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileListResponse>>>(0).onFailure(mock(), mock())
        }

        //when
        presenter.feedMobileList()

        //then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).closeRefresh()
        verify(view).showErrorMessage()
    }

    @Test
    fun `Test mobile feeddata Response`(){
        //given
        val mockMobileArray:ArrayList<MobileListResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createMobile(1,15.0,4.0))
        mockMobileArray.add(TestUtil.createMobile(2,10.5,8.1))
        mockMobileArray.add(TestUtil.createMobile(3,5.1,3.4))
        val call: retrofit2.Call<List<MobileListResponse>> = mock()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileListResponse>>>(0).onResponse(mock(), Response.success(mockMobileArray))
        }

        //when
        presenter.feedMobileList()
        val result = Deencapsulation.getField<ArrayList<MobileListResponse>>(presenter,"mobileArrayList")

        //then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).closeRefresh()
        verify(view).setPreFavorite()
        assertEquals(mockMobileArray[0].id,result[0].id)
    }
}