package com.azio.scholiki.model.datasource

/**
 * Created by Sahan Thinusha on 1/10/2018.
 */
interface BaseDataSource {
    interface BaseCallBack{
        fun onSuccess()
        fun onFailed(message : String)
    }
}