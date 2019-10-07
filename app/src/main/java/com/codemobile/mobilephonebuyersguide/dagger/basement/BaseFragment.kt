package com.codemobile.mobilephonebuyersguide.dagger.basement

import android.content.Context
import androidx.fragment.app.Fragment
import com.codemobile.mobilephonebuyersguide.dagger.application.InjectInterface
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(), InjectInterface {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}