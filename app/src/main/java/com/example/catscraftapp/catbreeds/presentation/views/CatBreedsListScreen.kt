package com.example.catscraftapp.catbreeds.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catscraftapp.R
import com.example.catscraftapp.navigation.Screens
import com.example.catscraftapp.catbreeds.presentation.state.CatBreedListState
import com.example.catscraftapp.catbreeds.presentation.views.components.ListItemView

@Composable
fun CatBreedsListScreen(
    modifier: Modifier,
    catBreedListState: CatBreedListState,
    navController: NavController,
    loadNextItems: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (catBreedListState.error != null) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                text = catBreedListState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (catBreedListState.breedList.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.cat_breeds_screen_title),
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    itemsIndexed(catBreedListState.breedList) { idx, item ->
                        if (idx >= catBreedListState.breedList.size - 1 && !catBreedListState.endReached && !catBreedListState.isLoading) {
                            loadNextItems()
                        }
                        ListItemView(
                            modifier = modifier.fillMaxWidth(),
                            imageUrl = item.image?.url,
                            title = item.name,
                            description = item.description,
                            onClick = {
                                navController.navigate(Screens.CatBreedDetailScreen.route + "/${item.id}")
                            }
                        )
                    }
                }

                if (catBreedListState.isLoading) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}