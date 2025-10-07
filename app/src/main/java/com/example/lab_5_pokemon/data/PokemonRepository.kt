package com.example.lab_5_pokemon.data

import com.example.lab_5_pokemon.network.Pokemon
import com.example.lab_5_pokemon.network.PokeApiService
import com.example.lab_5_pokemon.network.toDomain

class PokemonRepository(private val api: PokeApiService) {

    suspend fun getFirst100(): List<Pokemon> =
        api.getPokemonList(limit = 100).results.map { it.toDomain() }

    suspend fun getDetailById(id: String): Pokemon {
        val list = getFirst100()
        return list.first { it.id == id }
    }
}
