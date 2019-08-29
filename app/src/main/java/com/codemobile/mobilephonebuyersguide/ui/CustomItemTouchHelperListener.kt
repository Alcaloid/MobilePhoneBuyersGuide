package com.codemobile.mobilephonebuyersguide.ui

interface CustomItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}