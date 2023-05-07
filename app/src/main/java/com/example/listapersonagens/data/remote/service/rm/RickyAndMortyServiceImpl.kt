package com.example.listapersonagens.data.remote.service.rm

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.remote.service.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickyAndMortyServiceImpl : CharacterService {

    private val retrofitRickyAndMorty: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val rickyAndMortyService =
        retrofitRickyAndMorty.create(RickyAndMortyService::class.java)

    override suspend fun getCharacters(): List<Charackter> {
        val response = rickyAndMortyService.getCharacters()
        val rAndMModelChars = response.results
        return rAndMModelChars.map {
            Charackter(
                name = it.name,
                imageUrl = it.image
            )
        }
    }
}