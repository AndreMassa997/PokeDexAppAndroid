package com.example.pokedexapp.common.model

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
    @SerializedName("backdefault")
    val backDefault: String,

    @SerializedName("backfemale")
    val backFemale: String,
    @SerializedName("backshiny")
    val backShiny: String,
    @SerializedName("backshinyfemale")
    val backShinyFemale: String,
    @SerializedName("frontdefault")
    val frontDefault: String,
    @SerializedName("frontfemale")
    val frontFemale: String,
    @SerializedName("frontshiny")
    val frontShiny: String,
    @SerializedName("frontshinyfemale")
    val frontShinyFemale: String,
    val other: Other?
)

data class Other(
    val officialArtwork: OfficialArtwork,
)

data class OfficialArtwork(
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

enum class StatName{
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
enum class PokemonType{
    normal,
    fighting,
    flying,
    poison,
    ground,
    rock,
    bug,
    ghost,
    steel,
    fire,
    water,
    grass,
    electric,
    psychic,
    ice,
    dragon,
    dark,
    fairy,
    unknown,
    shadow,
}