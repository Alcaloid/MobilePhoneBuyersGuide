package com.codemobile.mobilephonebuyersguide.dagger.application

import android.app.Application
import com.codemobile.mobilephonebuyersguide.dagger.module.ActivityModule
import com.codemobile.mobilephonebuyersguide.dagger.module.CenterMobileModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        CenterMobileModule::class,
        ActivityModule::class
    ]
)
interface MobileComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MobileComponent
    }

    fun inject(smartApplication: MobileApplication)
}