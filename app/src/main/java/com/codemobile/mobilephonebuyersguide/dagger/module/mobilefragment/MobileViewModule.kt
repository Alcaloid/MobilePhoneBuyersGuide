package com.codemobile.mobilephonebuyersguide.dagger.module.mobilefragment

import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListContract
import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListFragment
import dagger.Binds
import dagger.Module

@Module
abstract class MobileViewModule {
    @Binds
    abstract fun MobileView(fragment: MobileListFragment): MobileListContract.MobileListView
}