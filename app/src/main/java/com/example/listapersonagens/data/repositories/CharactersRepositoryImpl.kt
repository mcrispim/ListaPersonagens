package com.example.listapersonagens.data.repositories

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType
import com.example.listapersonagens.data.remote.service.CharacterService

class CharactersRepositoryImpl(
    val disneyService: CharacterService,
    val rickyAndMortyService: CharacterService
) : CharactersRepository {

    override suspend fun getCharacters(type: CharacterType): List<Charackter> {
        val service = when (type) {
            CharacterType.DISNEY -> disneyService
            CharacterType.RICKY_AND_MORTY -> rickyAndMortyService
        }
        return service.getCharacters()
    }
}
