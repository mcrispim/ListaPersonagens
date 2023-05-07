package com.example.listapersonagens.data.remote.service.disney

data class DisneyCharactersResult(
    val data: List<Data>,
    val info: Info
)

data class Data(
    val __v: Int,
    val _id: Int,
    val allies: List<Any>,
    val createdAt: String,
    val enemies: List<Any>,
    val films: List<String>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<String>,
    val shortFilms: List<String>,
    val sourceUrl: String,
    val tvShows: List<String>,
    val updatedAt: String,
    val url: String,
    val videoGames: List<String>
)

data class Info(
    val count: Int,
    val nextPage: String,
    val previousPage: Any,
    val totalPages: Int
)
