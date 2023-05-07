package com.example.listapersonagens.data.remote.service

import com.example.listapersonagens.data.model.Charackter

interface CharacterService {

    suspend fun getCharacters(): List<Charackter>

}