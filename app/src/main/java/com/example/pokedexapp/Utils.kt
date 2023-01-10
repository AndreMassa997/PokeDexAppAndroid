package com.example.pokedexapp

fun String.capitalizeFirst(): String{
    return this.first().uppercase() + this.drop(1)
}