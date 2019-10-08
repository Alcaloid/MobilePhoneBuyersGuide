package com.codemobile.mobilephonebuyersguide.dagger.module

import com.codemobile.mobilephonebuyersguide.action.activity.MainActivity
import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.action.center.MessageFunction
import com.codemobile.mobilephonebuyersguide.dagger.application.MobileApplication
import com.codemobile.mobilephonebuyersguide.dagger.module.detail.DetailPresenterModule
import com.codemobile.mobilephonebuyersguide.dagger.module.detail.DetailViewModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailViewModule::class, DetailPresenterModule::class])
    abstract fun contributeDetailActivity(): DetailActivity
}