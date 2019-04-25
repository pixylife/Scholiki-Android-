package com.azio.scholiki.ui

import androidx.appcompat.app.AppCompatActivity

/**
 * Activity all Activity classes of rosso must extend. It provides required methods and presenter
 * instantiation and calls.
 */
abstract class BaseActivity <P : BasePresenter<*>>  : AppCompatActivity() {

    abstract val presenter: P

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }
}
