package com.gbilgeturk.pokemonapp.ui.screen.pokemonlist

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.gbilgeturk.pokemonapp.core.base.BaseViewModel
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.data.repo.PokemonRepo
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewEvent
import retrofit2.http.Url
import java.net.URL
import java.net.URLDecoder

class PokemonListViewModel(private val pokemonRepo: PokemonRepo) : BaseViewModel<PokemonListViewState, PokemonListViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    val pokemonPager = Pager(PagingConfig(pageSize = 20)) {
        UsersDataSource(pokemonRepo)
    }.flow.cachedIn(viewModelScope)


    override fun createInitialState()= PokemonListViewState()

    override fun onTriggerEvent(event: PokemonListViewEvent) {

    }


}

class UsersDataSource(
    private val repo: PokemonRepo
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getPokemonList(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun String.splitQuery(): Int? {
    val query_pairs: MutableMap<String, String> = LinkedHashMap()
    val query: String = URL(this).query
    val pairs = query.split("&").toTypedArray()
    for (pair in pairs) {
        val idx = pair.indexOf("=")
        query_pairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
    }
    val x = query_pairs.filterKeys { it == "offset"  }.values
    return x.first().toInt()
}

sealed class PokemonListViewEvent : IViewEvent {
    object OnLoading : PokemonListViewEvent()
    class OnPokemonList(val pokemonDto: List<Pokemon>) : PokemonListViewEvent()
}
