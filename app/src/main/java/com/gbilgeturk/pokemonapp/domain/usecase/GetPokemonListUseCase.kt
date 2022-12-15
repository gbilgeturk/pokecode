package com.gbilgeturk.pokemonapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gbilgeturk.pokemonapp.core.base.BaseUseCase
import com.gbilgeturk.pokemonapp.core.base.IParams
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.data.repo.PokemonRepo
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    internal val repository: PokemonRepo
) : BaseUseCase<GetPokemonListUseCase.Params,PagingData<Pokemon>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<Pokemon>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { PokemonPagingSource(repository) }
        ).flow
    }
}