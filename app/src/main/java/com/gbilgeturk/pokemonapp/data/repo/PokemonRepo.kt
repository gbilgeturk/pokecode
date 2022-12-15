package com.gbilgeturk.pokemonapp.data.repo

import android.content.Context
import com.gbilgeturk.pokemonapp.core.base.Empty
import com.gbilgeturk.pokemonapp.data.model.PokemonDetailResponse
import com.gbilgeturk.pokemonapp.data.model.PokemonListResponse
import com.gbilgeturk.pokemonapp.service.api.PokemonApi

interface PokemonRepo {
    suspend fun getPokemonList(page: Int) : PokemonListResponse
    suspend fun getPokemonDetail(name : String) : PokemonDetailResponse
    suspend fun setFavorite(isFavorite: Boolean) : Empty
}

class PokemonRepoImpl(
    private val context: Context,
    private val api: PokemonApi
) : PokemonRepo {
    override suspend fun getPokemonList(page: Int): PokemonListResponse{
        return api.fetchPokemonList(limit = 20, offset = page*20).body()!!
    }

    override suspend fun getPokemonDetail(name : String): PokemonDetailResponse {
        return api.fetchPokemonInfo(name).body()!!
    }

    override suspend fun setFavorite(isFavorite: Boolean): Empty {
        return Empty()
    }

}