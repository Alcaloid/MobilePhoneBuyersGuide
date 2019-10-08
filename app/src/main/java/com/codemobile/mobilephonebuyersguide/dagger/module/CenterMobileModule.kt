package com.codemobile.mobilephonebuyersguide.dagger.module

import android.content.Context
import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailActivity
import com.codemobile.mobilephonebuyersguide.action.activity.detail.DetailPresentation
import com.codemobile.mobilephonebuyersguide.action.center.MessageFunction
import com.codemobile.mobilephonebuyersguide.action.center.MyDagger
import com.codemobile.mobilephonebuyersguide.app.activity.detail.DetailContract
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CenterMobileModule {
    @Provides
    @Singleton
    fun provideMessageDialog(context: Context): MessageFunction {
        return MessageFunction(context)
    }

    @Provides
    @Singleton
    fun provideMyDagger(): MyDagger {
        return MyDagger()
    }
}