package com.example.listapersonagens.data.repositories

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType
import com.example.listapersonagens.data.remote.service.CharacterService
import com.example.listapersonagens.data.remote.service.disney.DisneyService
import com.example.listapersonagens.data.remote.service.disney.DisneyServiceImpl
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyService
import com.example.listapersonagens.data.remote.service.rm.RickyAndMortyServiceImpl

class CharactersRepositoryImpl(
    val disneyService: DisneyService,
    val rickyAndMortyService: RickyAndMortyService
) : CharactersRepository {

    override suspend fun getCharacters(type: CharacterType): List<Charackter> {
        val service = when (type) {
            CharacterType.DISNEY -> disneyService as CharacterService
            CharacterType.RICKY_AND_MORTY -> rickyAndMortyService as CharacterService
        }
        return service.getCharacters()
    }
}
