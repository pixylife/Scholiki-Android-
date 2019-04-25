package com.azio.scholiki.app.firebasedbconstats

/**
 * Firebase global constants listed here
 * Ex : Collection or Sub Collection names are defined here
 * Doing this might reduce maintance cost and time
 * *Plese get used to this kind of practices, it will improve your coding practice and also it will help your colleagues work also*
 * Created by Sahan Thinusha on 4/25/2019.
 */
enum class FirebaseConstants(val value : String) {
    STUDENT("student"),
    CLASS("Class"),
    INSTITUTE("Institute"),
    PARENT("Parent")
}