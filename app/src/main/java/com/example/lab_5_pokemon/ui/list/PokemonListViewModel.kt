package com.example.lab_5_pokemon.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_5_pokemon.data.PokemonRepository
import com.example.lab_5_pokemon.network.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface ListUiState {
    object Loading : ListUiState
    data class Success(val items: List<Pokemon>) : ListUiState
    data class Error(val msg: String) : ListUiState
}

class PokemonListViewModel(private val repo: PokemonRepository) : ViewModel() {
    private val _state = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val state: StateFlow<ListUiState> = _state

    init {
        viewModelScope.launch {
            try {
                _state.value = ListUiState.Success(repo.getFirst100())
            } catch (e: Exception) {
                _state.value = ListUiState.Error(e.message ?: "Error cargando lista")
            }
        }
    }
}
