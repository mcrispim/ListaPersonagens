package com.example.listapersonagens.data.repositories

import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType

interface CharactersRepository {
    suspend fun getCharacters(type: CharacterType): List<Charackter>

}