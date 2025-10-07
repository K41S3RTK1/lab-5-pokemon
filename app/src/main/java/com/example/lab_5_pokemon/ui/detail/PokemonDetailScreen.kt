package com.example.lab_5_pokemon.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonDetailScreen(vm: PokemonDetailViewModel) {
    val state by vm.state.collectAsState()

    when (state) {
        is DetailUiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        is DetailUiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text((state as DetailUiState.Error).msg) }
        is DetailUiState.Success -> {
            val p = (state as DetailUiState.Success).pokemon
            Column(Modifier.fillMaxSize().padding(16.dp)) {
                Text(p.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Angle("Front", p.imageUrlFront)
                    Angle("Back", p.imageUrlBack)
                }
                Spacer(Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Angle("Front Shiny", p.imageUrlShinyFront)
                    Angle("Back Shiny", p.imageUrlShinyBack)
                }
            }
        }
    }
}

@Composable
private fun Angle(label: String, url: String) {
    Column(Modifier.width(150.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label)
        Spacer(Modifier.height(8.dp))
        AsyncImage(model = url, contentDescription = label, modifier = Modifier.size(120.dp))
    }
}
