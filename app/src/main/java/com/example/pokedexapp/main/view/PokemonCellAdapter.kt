package com.example.pokedexapp.main.view

import android.content.Context
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
import com.example.pokedexapp.common.api.PokeAPI
import com.example.pokedexapp.main.viewModel.PokemonCellViewModel
import java.net.URL

class PokemonCellAdapter(private val dataSet: List<PokemonCellViewModel>) : RecyclerView.Adapter<PokemonCellAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val textView: TextView
        val cardView: CardView

        init {
            textView = view.findViewById(R.id.pokemon_cell_tv)
            imageView = view.findViewById(R.id.pokemon_cell_image)
            cardView = view.findViewById(R.id.pokemon_cell_card)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_cell, parent, false)
        this.context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet.elementAt(position).let { element ->
            holder.textView.text = element.name

            PokeAPI.instance.getImageFromURL(element.imageUrl) { image ->
                image?.let { image ->
                    holder.imageView.setImageBitmap(image)
                }
            }

            context?.let { context ->
                element.type?.let { type ->
                    holder.cardView.setCardBackgroundColor(type.getColor(context))
                }
            }
        }

    }
}