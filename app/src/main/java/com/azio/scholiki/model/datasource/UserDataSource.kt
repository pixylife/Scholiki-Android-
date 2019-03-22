package com.azio.scholiki.model.datasource

interface UserDataSource {
    fun userLogin(email : String, password : String)
}