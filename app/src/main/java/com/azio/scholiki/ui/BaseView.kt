package com.azio.scholiki.ui

import android.content.Context

/**
 * Base view any view must implement.
 */
interface BaseView{
    /**
     * @return the context in which the application is running
     */
    fun getContext(): Context

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}