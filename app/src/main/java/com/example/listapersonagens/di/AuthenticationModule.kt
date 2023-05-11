package com.example.listapersonagens.di

import com.example.listapersonagens.data.remote.authentication.Authenticator
import com.example.listapersonagens.data.remote.authentication.BackendAuthenticator
import com.example.listapersonagens.data.remote.authentication.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    @Named("backendAuthenticator")
    fun provideBackendAuthenticator() = BackendAuthenticator as Authenticator

    @Provides
    @Singleton
    @Named("firebaseAuthenticator")
    fun provideFirebaseAuthenticator() = FirebaseAuthenticator as Authenticator

}