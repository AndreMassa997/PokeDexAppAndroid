package com.example.pokedexapp.main.model

data class MainModel(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultModel>
)

data class ResultModel(
    val name: String,
    val url: String
)