package com.example.pokedexapp.main.view

import android.content.Context
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

class PokemonCellAdapter(private val dataSet: List<PokemonCellViewModel>) : RecyclerView.Adapter<PokemonCellAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val title: TextView
        val cardView: CardView
        val subtitle: TextView

        init {
            title = view.findViewById(R.id.pokemon_cell_title)
            imageView = view.findViewById(R.id.pokemon_cell_image)
            cardView = view.findViewById(R.id.pokemon_cell_card)
            subtitle = view.findViewById(R.id.pokemon_cell_subtitle)
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
            holder.title.text = element.name
            holder.subtitle.text = element.pokemonId

            PokeAPI.instance.getImageFromURL(element.imageUrl) { image ->
                image?.let {
                    holder.imageView.setImageBitmap(it)
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