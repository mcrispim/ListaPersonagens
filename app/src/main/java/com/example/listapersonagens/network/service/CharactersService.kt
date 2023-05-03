package com.example.listapersonagens.network.service

import com.example.listapersonagens.model.domain.Charackter

interface CharactersService {

    suspend fun getCharacters(): List<Charackter>

}