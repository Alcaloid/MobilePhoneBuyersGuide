package com.codemobile.mobilephonebuyersguide.api

import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListContract
import com.codemobile.mobilephonebuyersguide.fragment.mobilelist.MobileListPresentation
import com.codemobile.mobilephonebuyersguide.model.MobileListResponse
import com.nhaarman.mockitokotlin2.verify
import mockit.Deencapsulation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class APIUnitTesting {
    @Mock
    private lateinit var view: MobileListContract.MobileListView
    @Mock
    private lateinit var presenter: MobileListContract.MobileListPresentor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MobileListPresentation(view)
    }

    @Test
    fun `Test feeddata`(){
        //given

        //when
        presenter.feedMobileList()

        //then

    }
}