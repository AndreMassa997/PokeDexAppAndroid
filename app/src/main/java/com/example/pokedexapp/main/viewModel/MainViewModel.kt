package com.example.pokedexapp.main.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pokedexapp.common.api.PokeAPI
import com.example.pokedexapp.common.model.PokemonModel
import com.example.pokedexapp.main.model.MainModel
import com.google.gson.FieldNamingStrategy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var pokemons: MutableList<PokemonModel> = mutableListOf()
    private var nextOffset: Int = 0

    suspend fun getPokemons(offset: Int, onResult: (List<PokemonCellViewModel>) -> Unit){
        val queryParams = LinkedHashMap<String, String>()
        queryParams.put("offset", "$offset")
        PokeAPI.instance.get("pokemon", queryParams, true) { data ->
            data?.let { data ->
                val mainModel = parsePokemonsResponse(data)
                mainModel.results.forEach { result ->
                    CoroutineScope(Dispatchers.Default).launch {
                        getPokemon(result.name){ data ->
                            val pokemonCellViewModels = data.map { PokemonCellViewModel(it.name, it.types.firstOrNull()?.type?.name, it.sprites.other?.officialArtwork?.frontDefault ?: it.sprites.frontDefault, it.id) }
                            pokemonCellViewModels.sortedBy { it.id }
                            onResult(pokemonCellViewModels)
                        }
                    }
                }
            }
        }
    }

    private fun parsePokemonsResponse(data: StringBuffer): MainModel{
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.setFieldNamingStrategy(FieldNamingStrategy { f ->
            f.name.lowercase()
        }).create()
        val mainModel = gson.fromJson(data.toString(), MainModel::class.java)
        return mainModel
    }

    suspend fun getPokemon(pokemonNameOrId: String, onResult: (MutableList<PokemonModel>) -> Unit) {
        PokeAPI.instance.get("pokemon/$pokemonNameOrId", null, true) { data ->
            data?.let { it ->
                try {
                    pokemons.add(parsePokemonResponse(it))
                }catch (e: Exception){
                    Log.d("MAINVIEWMODEL", e.localizedMessage)
                }
                onResult(pokemons)
            }
        }
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