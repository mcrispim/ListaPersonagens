package com.example.listapersonagens.network.service

import com.example.listapersonagens.model.domain.Charackter

interface CharacterService {

    suspend fun getCharacters(): List<Charackter>

}