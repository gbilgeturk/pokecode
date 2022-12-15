package com.gbilgeturk.pokemonapp.ui.screen.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    navigateToDetail: (String?) -> Unit
) {
    val viewModel = koinViewModel<PokemonListViewModel>()
    val viewState by viewModel.uiState.collectAsState()

    PokemonList(viewModel = viewModel)

}

@Composable
fun PokemonList(viewModel: PokemonListViewModel) {
    val usersData = viewModel.pokemonPager.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(usersData) { pokemon ->

            Row(modifier = Modifier.fillMaxWidth().height(64.dp)) {
                PokemonNetworkImage(imageURL = pokemon?.getImageUrl())
                Text(modifier = Modifier.fillMaxWidth(), text = pokemon?.name ?: "Empty pokemon")

            }
        }
        when (usersData.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    //LoadingItem()
                }
            }
            is LoadState.Error -> {
                item {
                    //ErrorItem(message = (usersData.loadState.append as LoadState.Error).error.message.toString())
                }
            }
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PokemonNetworkImage(
    modifier: Modifier = Modifier,
    imageURL: Any?,
    placeholder: Int = 0,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Inside,
    crossFade: Boolean = false
) {
    val painter = rememberImagePainter(
        data = imageURL,
        builder = {
            crossfade(crossFade)
            size(OriginalSize)
            error(placeholder)
            fallback(placeholder)
        },
    )
    Box {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
        if (painter.state is ImagePainter.State.Loading)
            CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}