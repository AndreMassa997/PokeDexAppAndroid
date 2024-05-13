package com.example.pokedexapp.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.main.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel = MainViewModel()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)

        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }

        layoutManager.spanSizeLookup = spanSizeLookup
        recyclerView.layoutManager = layoutManager

        CoroutineScope(Dispatchers.Default).launch {
            mainViewModel.getPokemons(0) {
                val adapter = PokemonCellAdapter(it)
                runOnUiThread {
                    recyclerView.adapter = adapter
                    }
            }
        }
    }
}