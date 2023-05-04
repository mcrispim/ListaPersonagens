package com.example.listapersonagens.data.network.service

import com.example.listapersonagens.data.model.Charackter

interface CharacterService {

    suspend fun getCharacters(): List<Charackter>

}