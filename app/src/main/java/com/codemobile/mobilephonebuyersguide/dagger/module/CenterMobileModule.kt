package com.codemobile.mobilephonebuyersguide.dagger.module

import android.content.Context
import com.codemobile.mobilephonebuyersguide.action.center.MessageFunction
import com.codemobile.mobilephonebuyersguide.action.center.MyDagger
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