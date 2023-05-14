package com.example.listapersonagens.data.remote.service.disney

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.remote.service.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DisneyServiceImpl : CharacterService {

    private val retrofitDisney: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val disneyService: DisneyService = retrofitDisney.create(DisneyService::class.java)

    private fun DisneyCharactersResult.toCharacters(): List<Charackter> {
        return data.map {
            Charackter(
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }

    override suspend fun getCharacters(): List<Charackter> {
        val response = disneyService.getCharacters()
        return response.toCharacters()
    }
}