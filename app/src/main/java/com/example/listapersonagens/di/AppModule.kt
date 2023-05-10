package com.example.listapersonagens.di

import com.example.listapersonagens.data.remote.service.disney.DisneyService
import com.example.listapersonagens.data.remote.service.disney.DisneyServiceImpl
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyService
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyServiceImpl
import com.example.listapersonagens.data.repositories.CharactersRepository
import com.example.listapersonagens.data.repositories.CharactersRepositoryImpl
import com.example.listapersonagens.data.repositories.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDisneyService() = DisneyServiceImpl() as DisneyService

    @Provides
    @Singleton
    fun provideRickyAndMortyService() = RickyAndMortyServiceImpl() as RickyAndMortyService

    @Provides
    @Singleton
    fun provideCharacterRepository(
        disneyService: DisneyService,
        rAndMService: RickyAndMortyService
    ): CharactersRepository = CharactersRepositoryImpl(disneyService, rAndMService)

    @Provides
    @Singleton
    fun provideLoginRepository() = LoginRepositoryImpl()
}