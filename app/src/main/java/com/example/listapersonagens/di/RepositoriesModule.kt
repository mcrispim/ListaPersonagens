package com.example.listapersonagens.di

import com.example.listapersonagens.data.remote.authentication.Authenticator
import com.example.listapersonagens.data.remote.service.CharacterService
import com.example.listapersonagens.data.repositories.CharactersRepository
import com.example.listapersonagens.data.repositories.CharactersRepositoryImpl
import com.example.listapersonagens.data.repositories.LoginRepository
import com.example.listapersonagens.data.repositories.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @Provides
    @ViewModelScoped
    fun provideCharacterRepository(
        @Named("disneyService") disneyService: CharacterService,
        @Named("rAndMService") rAndMService: CharacterService
    ): CharactersRepository = CharactersRepositoryImpl(disneyService, rAndMService)

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(
        @Named("backendAuthenticator") backend: Authenticator,
        @Named("firebaseAuthenticator") firebase: Authenticator
    ): LoginRepository = LoginRepositoryImpl(backend, firebase)

}