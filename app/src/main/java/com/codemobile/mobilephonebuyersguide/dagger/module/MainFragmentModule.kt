package com.codemobile.mobilephonebuyersguide.dagger.module

import com.codemobile.mobilephonebuyersguide.action.fragment.favorite.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListFragment
import com.codemobile.mobilephonebuyersguide.dagger.module.mobilefragment.MobilePresenterModule
import com.codemobile.mobilephonebuyersguide.dagger.module.mobilefragment.MobileViewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {
    @ContributesAndroidInjector(modules = [MobilePresenterModule::class,MobileViewModule::class])
    abstract fun contributesMobileList(): MobileListFragment

    @ContributesAndroidInjector
    abstract fun contributesFavorite(): FavouriteFragment
}