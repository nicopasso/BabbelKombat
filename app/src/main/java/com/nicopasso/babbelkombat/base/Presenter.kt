package com.nicopasso.babbelkombat.base

interface Presenter<V: BaseView> {

    fun attachView(baseView: V)

    fun detachView()

}