package com.example.pokedexapp.main.view

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.main.viewModel.PokemonCellViewModel
import java.net.URL

class PokemonCell(private val dataSet: Array<PokemonCellViewModel>):
    RecyclerView.Adapter<PokemonCell.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val imageView: ImageView
            val textView: TextView
            val cardView: CardView

            init {
                textView = view.findViewById(R.id.pokemon_cell_tv)
                imageView = view.findViewById(R.id.pokemon_cell_image)
                cardView = view.findViewById(R.id.pokemon_cell_card)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].name
        val url = URL( dataSet[position].imageUrl)
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        holder.imageView.setImageBitmap(image)
        holder.cardView.setCardBackgroundColor(dataSet[position].type.color())

    }
}