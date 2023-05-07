package com.example.listapersonagens.data.repositories

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType
import com.example.listapersonagens.data.remote.service.disney.DisneyServiceImpl
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyServiceImpl

class CharactersRepository {
    private val disneyService = DisneyServiceImpl()
    private val rickyAndMortyService = RickyAndMortyServiceImpl()

    suspend fun getCharacters(type: CharacterType): List<Charackter> {
        return when(type) {
            CharacterType.DISNEY -> disneyService.getCharacters()
            CharacterType.RICKY_AND_MORTY -> rickyAndMortyService.getCharacters()
        }
    }
}