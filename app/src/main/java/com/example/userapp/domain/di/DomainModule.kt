package com.example.userapp.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
/*    @Singleton
    *//*@Provides*/

   /* fun provideRepo(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore): Repo{
        return RepoImpl(firebaseAuth,firestore)
    }*/
}