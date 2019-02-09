package com.azio.scholiki.ui

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.**/
open class BasePresenter<T> {

    var mView: T? = null

    fun attachView(view: T) {
        this.mView = view
    }

    fun detachView() {
        if (mView != null) {
            mView = null
        }
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }
}