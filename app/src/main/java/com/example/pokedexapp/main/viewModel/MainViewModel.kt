package com.example.pokedexapp.main.viewModel

import androidx.lifecycle.ViewModel
import com.example.pokedexapp.common.api.PokeAPI
import com.example.pokedexapp.common.model.PokemonModel
import com.example.pokedexapp.common.model.PokemonType
import com.example.pokedexapp.main.model.MainModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel: ViewModel() {

    var pokemons: MutableList<PokemonModel> = mutableListOf<PokemonModel>()
    private var nextOffset: Int = 0

    suspend fun getPokemons(offset: Int, onResult: () -> Unit){
        val queryParams = LinkedHashMap<String, String>()
        queryParams.put("offset", "$offset")
        PokeAPI.instance.get("pokemon", queryParams, true, { data ->
            data?.let {
                parsePokemonsResponse(data)
            }
        })
    }

    private fun parsePokemonsResponse(data: StringBuffer): MainModel{
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.setFieldNamingStrategy(FieldNamingStrategy { f ->
            f.name.lowercase()
        }).create()
        val mainModel = gson.fromJson(data.toString(), MainModel::class.java)
        return mainModel
    }

    suspend fun getPokemon(pokemonNameOrId: String, onResult: () -> Unit) {
        PokeAPI.instance.get("pokemon/$pokemonNameOrId", null, true, { data ->
            data?.let {
                pokemons.add(parsePokemonResponse(it))
            }
        })
    }

    private fun parsePokemonResponse(data: StringBuffer): PokemonModel{
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.setFieldNamingStrategy(FieldNamingStrategy { f ->
            f.name.lowercase()
        }).create()
        val pokemonModel = gson.fromJson(data.toString(), PokemonModel::class.java)
        return pokemonModel
    }
}