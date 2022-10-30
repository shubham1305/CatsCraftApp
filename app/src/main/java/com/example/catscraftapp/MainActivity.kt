package com.example.catscraftapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catscraftapp.catbreeds.presentation.CatBreedsViewModel
import com.example.catscraftapp.catbreeds.presentation.views.CatBreedDetailScreen
import com.example.catscraftapp.catbreeds.presentation.views.CatBreedsListScreen
import com.example.catscraftapp.common.AppConstants
import com.example.catscraftapp.navigation.Screens
import com.example.catscraftapp.ui.theme.CatsCraftAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsCraftAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val catBreedsViewModel: CatBreedsViewModel = viewModel()
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screens.CatBreedsListScreen.route
                    ) {
                        composable(Screens.CatBreedsListScreen.route) {
                            CatBreedsListScreen(
                                modifier = Modifier.fillMaxSize(),
                                catBreedListState = catBreedsViewModel.catBreedsListState,
                                navController = navController,
                                loadNextItems = {
                                    catBreedsViewModel.loadNextItems()
                                }
                            )
                        }

                        composable(Screens.CatBreedDetailScreen.route + "/{${AppConstants.CAT_BREED_ID}}") {
                            CatBreedDetailScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController,
                                viewModel = catBreedsViewModel,
                                openUrl = {
                                    launchUrlIntent(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }


    private fun launchUrlIntent(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}