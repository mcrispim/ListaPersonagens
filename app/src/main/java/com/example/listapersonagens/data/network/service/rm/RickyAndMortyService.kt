package com.example.listapersonagens.data.network.service.rm

import retrofit2.http.GET

interface RickyAndMortyService {
    
    @GET("character")
    suspend fun getCharacters(): RickyAndMortyCharactersResult
    
}