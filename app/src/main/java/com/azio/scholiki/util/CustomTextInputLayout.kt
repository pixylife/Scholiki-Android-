package com.azio.scholiki.util

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.support.design.widget.TextInputLayout

/**
 * Created by Sahan Thinusha on 4/7/2019.
 */
class CustomTextInputLayout (context: Context, attrs: AttributeSet) :TextInputLayout(context, attrs) {

    override fun setErrorEnabled(enabled: Boolean) {
        super.setErrorEnabled(enabled)

        if (!enabled) {
            return
        }

        try {
            val layout = this
            val errorView : TextView = ((this.getChildAt(1) as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(0) as TextView

            (layout.getChildAt(1) as ViewGroup).layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            (layout.getChildAt(1) as ViewGroup).getChildAt(0).layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT

            errorView.gravity = Gravity.END

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}