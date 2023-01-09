package com.example.pokedexapp.common.model

import android.content.Context
import com.example.pokedexapp.R
import androidx.core.content.ContextCompat
import com.google.gson.annotations.SerializedName

data class PokemonModel(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val sprites: Sprites,
    val stats: List<Stats>,
    val types: List<TypeElement>
)

data class Sprites(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_female")
    val backFemale: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String,
    val other: Other?
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork,
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)

data class Ability(
    val ability: Result,
    val isHidden: Boolean
)

data class Result(
    val name: String
)

data class Stats(
    @SerializedName("basestat")
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: StatName
)

enum class StatName {
    hp,
    attack,
    defense,
    specialAttack,
    specialDefense,
    speed
}

data class TypeElement(
    val type: Type
)

data class Type(
    val name: PokemonType
)

enum class PokemonType {
    normal {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.normal)
    },
    fighting {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.fighting)
    },
    flying {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.flying)

    },
    poison {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.poison)

    },
    ground {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.ground)
    },
    rock {
        override fun getColor(context: Context): Int = ContextCompat.getColor(context, R.color.rock)

    },
    bug {
        override fun getColor(context: Context): Int = ContextCompat.getColor(context, R.color.bug)
    },
    ghost {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.ghost)
    },
    steel {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.steel)
    },
    fire {
        override fun getColor(context: Context): Int = ContextCompat.getColor(context, R.color.fire)
    },
    water {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.water)

    },
    grass {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.grass)
    },
    electric {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.electric)

    },
    psychic {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.psychic)

    },
    ice {
        override fun getColor(context: Context): Int = ContextCompat.getColor(context, R.color.ice)
    },
    dragon {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.dragon)
    },
    dark {
        override fun getColor(context: Context): Int = ContextCompat.getColor(context, R.color.dark)
    },
    fairy {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.fairy)
    },
    shadow {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.shadow)
    },
    unknown {
        override fun getColor(context: Context): Int =
            ContextCompat.getColor(context, R.color.unknown)
    };

    abstract fun getColor(context: Context): Int
}