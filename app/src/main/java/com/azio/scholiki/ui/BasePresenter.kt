package com.azio.scholiki.ui

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.**/
interface BasePresenter<V : BaseView> {
    val view: V
    fun subscribe() {}
    fun unSubscribe() {}
}