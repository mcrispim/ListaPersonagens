package com.example.listapersonagens.network.service.disney

data class DisneyCharactersResult(
    val data: List<DisneyCharacter>
)

data class DisneyCharacter(
    val name: String,
    val imageUrl: String
)
