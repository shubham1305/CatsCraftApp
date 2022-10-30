package com.example.catscraftapp.navigation

sealed class Screens(val route: String) {
    object CatBreedsListScreen: Screens("cat_breeds_list_screen")
    object CatBreedDetailScreen: Screens("cat_breed_detail_screen")
}