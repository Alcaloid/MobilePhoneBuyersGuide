package com.codemobile.mobilephonebuyersguide.dagger.module.mobilefragment

import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListContract
import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListPresentation
import dagger.Module
import dagger.Provides

@Module
class MobilePresenterModule {

    @Provides
    fun MobilePresentation(view: MobileListContract.MobileListView): MobileListContract.MobileListPresenter {
        return MobileListPresentation(view)
    }
}