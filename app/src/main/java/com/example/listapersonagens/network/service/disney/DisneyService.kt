package com.example.listapersonagens.network.service.disney

import com.example.listapersonagens.model.api.DisneyCharactersResult
import retrofit2.http.GET

interface DisneyService {
    
    @GET("characters")
    suspend fun getCharacters(): DisneyCharactersResult
    
}