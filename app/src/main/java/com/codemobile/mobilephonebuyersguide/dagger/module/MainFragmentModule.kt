package com.codemobile.mobilephonebuyersguide.dagger.module

import com.codemobile.mobilephonebuyersguide.action.fragment.favorite.FavouriteFragment
import com.codemobile.mobilephonebuyersguide.action.fragment.mobilelist.MobileListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesMobileList(): MobileListFragment

    @ContributesAndroidInjector
    abstract fun contributesFavorite(): FavouriteFragment
}