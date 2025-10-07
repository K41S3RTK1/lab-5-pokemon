package com.example.lab_5_pokemon.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab_5_pokemon.network.Pokemon

@Composable
fun PokemonListScreen(
    vm: PokemonListViewModel,
    onClickItem: (String) -> Unit
) {
    val state by vm.state.collectAsState()

    when (state) {
        is ListUiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is ListUiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text((state as ListUiState.Error).msg)
        }
        is ListUiState.Success -> {
            val items = (state as ListUiState.Success).items
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { p ->
                    PokemonItemCard(p) { onClickItem(p.id) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonItemCard(p: Pokemon, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = p.imageUrlFront, contentDescription = p.name, modifier = Modifier.size(56.dp))
            Spacer(Modifier.width(16.dp))
            Text(text = p.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}
