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
import com.example.catscraftapp.catbreeds.presentation.state.CatsListState
import com.example.catscraftapp.ui.components.ListItemView

@Composable
fun CatBreedsListScreen(
    modifier: Modifier,
    catsListState: CatsListState,
    navController: NavController,
    loadNextItems: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (catsListState.breedList.isEmpty() && catsListState.error != null) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                text = catsListState.error,
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
                if (catsListState.breedList.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.cat_breeds_screen_title),
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    itemsIndexed(catsListState.breedList) { idx, item ->
                        if (idx >= catsListState.breedList.size - 1 && !catsListState.endReached && !catsListState.isLoading) {
                            loadNextItems()
                        }
                        ListItemView(
                            modifier = modifier.fillMaxWidth(),
                            imageUrl = item.imageUrl,
                            title = item.name,
                            description = item.description,
                            onClick = {
                                navController.navigate(Screens.CatBreedDetailScreen.route + "/${item.id}")
                            }
                        )
                    }

                    if (catsListState.error != null) {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.Center),
                                text = catsListState.error,
                                color = MaterialTheme.colors.error,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                if (catsListState.isLoading) {
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