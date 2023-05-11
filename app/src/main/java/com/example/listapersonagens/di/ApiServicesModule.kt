package com.example.listapersonagens.di

import com.example.listapersonagens.data.remote.service.CharacterService
import com.example.listapersonagens.data.remote.service.disney.DisneyServiceImpl
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Provides
    @Singleton
    @Named("disneyService")
    fun provideDisneyServiceImpl() = DisneyServiceImpl() as CharacterService

    @Provides
    @Singleton
    @Named("rAndMService")
    fun provideRickyAndMortyServiceImpl() = RickyAndMortyServiceImpl() as CharacterService

}