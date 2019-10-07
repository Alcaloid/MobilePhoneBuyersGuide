package com.codemobile.mobilephonebuyersguide.action.center

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.codemobile.mobilephonebuyersguide.R

class MessageFunction(var context: Context) {

    fun getMessage():String{
        return context.getString(R.string.app_name)
    }
}