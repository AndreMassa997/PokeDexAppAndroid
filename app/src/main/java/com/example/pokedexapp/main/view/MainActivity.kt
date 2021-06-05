package com.example.pokedexapp.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedexapp.R
import com.example.pokedexapp.main.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {
            //mainViewModel.getPokemon("1",{

           // })
            mainViewModel.getPokemons(0, {

            })
        }

    }
}