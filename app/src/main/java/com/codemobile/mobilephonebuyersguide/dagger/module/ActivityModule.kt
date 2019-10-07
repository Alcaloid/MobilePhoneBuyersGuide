package com.codemobile.mobilephonebuyersguide.dagger.module

import com.codemobile.mobilephonebuyersguide.action.activity.MainActivity
import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}