package com.example.lab_5_pokemon.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_5_pokemon.data.PokemonRepository
import com.example.lab_5_pokemon.network.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val pokemon: Pokemon) : DetailUiState
    data class Error(val msg: String) : DetailUiState
}

class PokemonDetailViewModel(
    private val repo: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id: String = checkNotNull(savedStateHandle["id"])

    private val _state = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val state: StateFlow<DetailUiState> = _state

    init {
        viewModelScope.launch {
            try {
                _state.value = DetailUiState.Success(repo.getDetailById(id))
            } catch (e: Exception) {
                _state.value = DetailUiState.Error(e.message ?: "Error cargando detalle")
            }
        }
    }
}
