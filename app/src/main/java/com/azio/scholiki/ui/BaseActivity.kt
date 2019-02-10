package com.azio.scholiki.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.azio.scholiki.app.ScholikiApplication
import com.azio.scholiki.dagger.AppComponent
import javax.inject.Inject
import android.support.v7.app.AppCompatActivity

/**
 * Activity all Activity classes of rosso must extend. It provides required methods and presenter
 * instantiation and calls.
 */
abstract class BaseActivity<TPresenter : BasePresenter<TView>, TView : BaseView> : AppCompatActivity(), BaseView {

    @set : Inject
    var mPresenter: TPresenter? = null

    @set : Inject
    lateinit var mView: TView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDependencies((application as ScholikiApplication).getAppComponent())
    }

    protected abstract fun initDependencies(appComponent: AppComponent)


    override fun onCreateView(parent: View, name: String, context: Context, attrs: AttributeSet): View {
        if (mPresenter != null) {
            if(::mView.isInitialized){
                mPresenter!!.attachView(mView)
            }
        }

        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }

        super.onDestroy()
    }
}
