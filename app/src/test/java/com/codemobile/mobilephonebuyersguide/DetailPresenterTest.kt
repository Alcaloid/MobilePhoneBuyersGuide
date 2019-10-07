package com.codemobile.mobilephonebuyersguide

import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailPresentation
import com.codemobile.mobilephonebuyersguide.action.internet.ApiInterface
import com.codemobile.mobilephonebuyersguide.action.model.ImageResponse
import com.codemobile.mobilephonebuyersguide.action.model.MobileListResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Callback
import retrofit2.Response

class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailContract.DetailView
    @Mock
    private lateinit var presenter: DetailContract.DetailPresentation
    @Mock
    private lateinit var service: ApiInterface

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresentation(
            view,
            service
        )
    }

    @Test
    fun `show detail data`() {
        //given
        val mobile: MobileListResponse = TestUtil.createMobile(
            id = 1,
            name = "TestingMobile",
            price = 10.0,
            rating = 20.0,
            brand = "Silver",
            description = "Description"
        )

        //when
        presenter.getPassData(mobile)

        //then
        verify(view).setName(mobile.name)
        verify(view).setBrand(mobile.brand)
        verify(view).setDescription(mobile.description)
        verify(view).setPrice(mobile.price.toString())
        verify(view).setRate(mobile.rating.toString())
    }

    @Test
    fun `feed image detail data Fail`(){
        //given
        val call: retrofit2.Call<List<MobileListResponse>> = mock()
        whenever(service.getMobileList()).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<MobileListResponse>>>(0).onFailure(mock(), mock())
        }

        //when
        presenter.feedImageDetail(1)

        //then
        verify(view).showErrorMessage()
    }

    @Test
    fun `feed image detail data Response`(){
        //given
        val mockMobileArray:ArrayList<ImageResponse> = arrayListOf()
        mockMobileArray.add(TestUtil.createImageMobile(1,url = "Image1"))
        mockMobileArray.add(TestUtil.createImageMobile(2,url = "Image2"))
        mockMobileArray.add(TestUtil.createImageMobile(3,url = "Image3"))
        val call: retrofit2.Call<List<ImageResponse>> = mock()
        whenever(service.getMobileImage(any())).thenReturn(call)
        whenever(call.enqueue(any())).thenAnswer {
            it.getArgument<Callback<List<ImageResponse>>>(0).onResponse(mock(), Response.success(mockMobileArray))
        }

        //when
        presenter.feedImageDetail(mock())

        //then
        verify(view).showImageMobileList(mockMobileArray)
    }
}