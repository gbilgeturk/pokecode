package com.gbilgeturk.pokemonapp.domain.usecase

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.data.repo.PokemonRepo
import java.io.IOException

class PokemonPagingSource(
    private val repository: PokemonRepo
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        return try {
            val response = repository.getPokemonList(page)
            val characterList = response.results



            LoadResult.Page(
                data = characterList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characterList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}