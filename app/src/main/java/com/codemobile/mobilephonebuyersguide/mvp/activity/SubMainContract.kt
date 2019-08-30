package com.codemobile.mobilephonebuyersguide.mvp.activity

interface SubMainContract {
    interface SubMainView{
        fun showPage()

    }

    interface SubMainPresenter{
        fun getPage()
    }
}