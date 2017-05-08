package com.nicopasso.babbelkombat.base

open class BasePresenter<T: BaseView>: Presenter<T> {

    var mBaseView: T? = null

    override fun attachView(baseView: T) {
        mBaseView = baseView
    }

    override fun detachView() {
        mBaseView = null
    }

    fun getView(): T? = mBaseView

}