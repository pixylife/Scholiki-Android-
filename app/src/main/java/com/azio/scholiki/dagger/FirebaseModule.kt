package com.azio.scholiki.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about Firebase
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
object FirebaseModule {

    @Provides
    @Singleton
    internal fun provideFirebaseAuth(): FirebaseAuth {
        // Initialize Firebase Auth
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    internal fun provideFireStore(): FirebaseFirestore {
        // Initialize Firebase db
        return FirebaseFirestore.getInstance()
    }

  /*  @Provides
    @Singleton
    internal fun provideStorage(): FirebaseStorage {
        // Initialize Firebase db
        return FirebaseStorage.getInstance()

    }*/
}