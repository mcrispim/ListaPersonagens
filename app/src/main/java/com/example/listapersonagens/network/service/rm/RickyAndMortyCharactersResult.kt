package com.example.listapersonagens.network.service.rm

data class RickyAndMortyCharacters(
    val results: List<RickyAndMortyCharacter>
)

data class RickyAndMortyCharacter (
    val name: String,
    val image: String
)
