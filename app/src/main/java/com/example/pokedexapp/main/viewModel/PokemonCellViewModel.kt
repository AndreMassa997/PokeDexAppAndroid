package com.example.pokedexapp.main.viewModel

import androidx.lifecycle.ViewModel
import com.example.pokedexapp.capitalizeFirst
import com.example.pokedexapp.common.model.PokemonType

class PokemonCellViewModel(name: String, type: PokemonType?, imageUrl: String?, id: Int): ViewModel()  {

    val name: String
    val type: PokemonType?
    val imageUrl: String?
    val id: Int

    init {
        this.name = name.capitalizeFirst()
        this.type = type
        this.imageUrl = imageUrl
        this.id = id
    }

    val pokemonId: String
        get() = String.format("#%03d", id)


}