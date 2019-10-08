package com.codemobile.mobilephonebuyersguide.action.center

import android.content.Context
import android.util.Log
import com.codemobile.mobilephonebuyersguide.R

class MessageFunction(var context: Context) {
    fun getMessage(): String {
        return context.getString(R.string.app_name)
    }
}

class MyDagger() {

    fun printSomething() {
        Log.d("Dagger", "something")
    }

    fun getOverHere() = "OverHere"
}