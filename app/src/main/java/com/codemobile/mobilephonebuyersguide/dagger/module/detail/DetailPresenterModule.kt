package com.codemobile.mobilephonebuyersguide.dagger.module.detail

import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailPresentation
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import dagger.Module
import dagger.Provides

@Module
class DetailPresenterModule {

    @Provides
    fun bindDetailPresenter(view: DetailContract.DetailView): DetailContract.DetailPresentation {
        return DetailPresentation(view)
    }

}