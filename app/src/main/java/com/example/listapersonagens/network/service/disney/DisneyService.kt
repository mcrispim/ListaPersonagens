package com.example.listapersonagens.network.service.disney

import retrofit2.http.GET

interface DisneyService {
    
    @GET("characters")
    suspend fun getCharacters(): DisneyCharactersResult
    
}