package com.example.listapersonagens.data.network.service.disney

import retrofit2.http.GET

interface DisneyService {
    
    @GET("character")
    suspend fun getCharacters(): DisneyCharactersResult
    
}