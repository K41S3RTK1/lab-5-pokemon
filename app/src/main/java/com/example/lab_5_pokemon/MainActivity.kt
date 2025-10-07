package com.example.lab_5_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_5_pokemon.data.PokemonRepository
import com.example.lab_5_pokemon.network.RetrofitClient
import com.example.lab_5_pokemon.ui.detail.PokemonDetailScreen
import com.example.lab_5_pokemon.ui.detail.PokemonDetailViewModel
import com.example.lab_5_pokemon.ui.list.PokemonListScreen
import com.example.lab_5_pokemon.ui.list.PokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = PokemonRepository(RetrofitClient.apiService)

        setContent {
            MaterialTheme {
                val nav = rememberNavController()

                Scaffold(
                    topBar = { TopAppBar(title = { Text("Pokedex") }) }
                ) { innerPadding ->
                    NavHost(
                        navController = nav,
                        startDestination = "list",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("list") {
                            val vm: PokemonListViewModel = viewModel(
                                factory = SimpleFactory { PokemonListViewModel(repo) }
                            )
                            PokemonListScreen(vm) { id ->
                                nav.navigate("detail/$id")
                            }
                        }
                        composable(
                            route = "detail/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            val vm: PokemonDetailViewModel = viewModel(
                                factory = SimpleFactorySaved { handle ->
                                    handle["id"] = id
                                    PokemonDetailViewModel(repo, handle)
                                }
                            )
                            PokemonDetailScreen(vm)
                        }
                    }
                }
            }
        }
    }
}
