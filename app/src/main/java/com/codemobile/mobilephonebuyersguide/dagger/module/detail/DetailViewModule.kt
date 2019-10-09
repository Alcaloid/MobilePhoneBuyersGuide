package com.codemobile.mobilephonebuyersguide.dagger.module.detail

import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import dagger.Binds
import dagger.Module

@Module
abstract class DetailViewModule {
    @Binds
    abstract fun bindDetailView(activity: DetailActivity): DetailContract.DetailView
}