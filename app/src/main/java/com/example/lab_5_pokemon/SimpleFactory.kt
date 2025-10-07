package com.example.lab_5_pokemon

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SimpleFactory<T : ViewModel>(private val create: () -> T) : ViewModelProvider.Factory {
    override fun <T2 : ViewModel> create(modelClass: Class<T2>): T2 {
        @Suppress("UNCHECKED_CAST") return create() as T2
    }
}

class SimpleFactorySaved<T : ViewModel>(
    private val create: (SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory() {
    override fun <T2 : ViewModel> create(
        key: String, modelClass: Class<T2>, handle: SavedStateHandle
    ): T2 {
        @Suppress("UNCHECKED_CAST") return create(handle) as T2
    }
}
