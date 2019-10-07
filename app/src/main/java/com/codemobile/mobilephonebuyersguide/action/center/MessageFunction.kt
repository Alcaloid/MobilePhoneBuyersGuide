package com.codemobile.mobilephonebuyersguide.action.center

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class MessageFunction(var context: Context) {

    fun showErrorMessage():AlertDialog{
        return AlertDialog  .Builder(context)
                            .setTitle("Error")
                            .setMessage("Can't feed mobile data")
                            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ -> })
                            .create()
    }
}