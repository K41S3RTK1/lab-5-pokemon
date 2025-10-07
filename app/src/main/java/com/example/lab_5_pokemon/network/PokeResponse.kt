package com.example.lab_5_pokemon.network


data class PokeResponseDto(val results: List<PokemonDto>) {
    @Suppress("unused") constructor() : this(emptyList())
}

data class PokemonDto(
    val name: String,
    val url: String
) {
    @Suppress("unused") constructor() : this("", "")
}


data class Pokemon(
    val name: String,
    val id: String
) {
    @Suppress("unused") constructor() : this("", "")

    val imageUrlFront: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val imageUrlBack: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    val imageUrlShinyFront: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    val imageUrlShinyBack: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}


fun PokemonDto.toDomain(): Pokemon {
    val id = url.trimEnd('/').split("/").last()
    return Pokemon(name.replaceFirstChar { it.uppercase() }, id)
}
