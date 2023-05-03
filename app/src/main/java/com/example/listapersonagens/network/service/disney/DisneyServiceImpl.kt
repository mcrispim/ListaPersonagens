package com.example.listapersonagens.network.service.disney

import com.example.listapersonagens.model.domain.Charackter
import com.example.listapersonagens.network.service.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DisneyServiceImpl : CharacterService {

    private val retrofitDisney: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val disneyService: DisneyService = retrofitDisney.create(DisneyService::class.java)

    override suspend fun getCharacters(): List<Charackter> {
        val response = disneyService.getCharacters()
        val disneyModelChars = response.data
        return disneyModelChars.map {
            Charackter(
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }
}