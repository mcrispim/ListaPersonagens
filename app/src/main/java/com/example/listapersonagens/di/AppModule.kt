package com.example.listapersonagens.di

import com.example.listapersonagens.data.remote.authentication.Authenticator
import com.example.listapersonagens.data.remote.authentication.BackendAuthenticator
import com.example.listapersonagens.data.remote.authentication.FirebaseAuthenticator
import com.example.listapersonagens.data.remote.service.CharacterService
import com.example.listapersonagens.data.remote.service.disney.DisneyServiceImpl
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyServiceImpl
import com.example.listapersonagens.data.repositories.CharactersRepository
import com.example.listapersonagens.data.repositories.CharactersRepositoryImpl
import com.example.listapersonagens.data.repositories.LoginRepository
import com.example.listapersonagens.data.repositories.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("disneyService")
    fun provideDisneyServiceImpl() = DisneyServiceImpl() as CharacterService

    @Provides
    @Singleton
    @Named("rAndMService")
    fun provideRickyAndMortyServiceImpl() = RickyAndMortyServiceImpl() as CharacterService

    @Provides
    @Singleton
    fun provideCharacterRepository(
        @Named("disneyService") disneyService: CharacterService,
        @Named("rAndMService") rAndMService: CharacterService
    ): CharactersRepository = CharactersRepositoryImpl(disneyService, rAndMService)

    @Provides
    @Singleton
    @Named("backendAuthenticator")
    fun provideBackendAuthenticator() = BackendAuthenticator as Authenticator

    @Provides
    @Singleton
    @Named("firebaseAuthenticator")
    fun provideFirebaseAuthenticator() = FirebaseAuthenticator as Authenticator

    @Provides
    @Singleton
    fun provideLoginRepository(
        @Named("backendAuthenticator") backend: Authenticator,
        @Named("firebaseAuthenticator") firebase: Authenticator
    ): LoginRepository = LoginRepositoryImpl(backend, firebase)
}