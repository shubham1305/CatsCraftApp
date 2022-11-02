package com.example.catscraftapp.catbreeds.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catscraftapp.R
import com.example.catscraftapp.catbreeds.presentation.CatsViewModel
import com.example.catscraftapp.common.AppConstants
import com.example.catscraftapp.ui.components.CustomImageView
import com.example.catscraftapp.ui.components.ItemPropertyView

@Composable
fun CatBreedDetailScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: CatsViewModel,
    openUrl: (String) -> Unit
) {
    val breedId =
        navController.currentBackStackEntry?.arguments?.get(AppConstants.CAT_BREED_ID).toString()
    val catBreed = remember {
        viewModel.catBreedsListState.breedList.firstOrNull { it.id == breedId }
    }

    catBreed?.let {
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CustomImageView(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp)),
                imageUrl = it.imageUrl,
                scale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = it.name + (it.altNames?.takeIf { it.isNotBlank() }
                    ?.let { "\na.k.a\n$it" } ?: ""),
                style = MaterialTheme.typography.h5.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    ItemPropertyView(
                        modifier = Modifier.weight(1f),
                        name = stringResource(R.string.label_animal_origin),
                        value = it.origin
                    )
                    ItemPropertyView(
                        modifier = Modifier.weight(1f),
                        name = stringResource(R.string.label_animal_weight),
                        value = stringResource(id = R.string.value_animal_weight, it.weight)
                    )
                    ItemPropertyView(
                        modifier = Modifier.weight(1f),
                        name = stringResource(R.string.label_animal_lifespan),
                        value = stringResource(id = R.string.value_animal_lifespan, it.lifeSpan)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_animal_description),
                        style = MaterialTheme.typography.h6.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.body1.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            it.wikipediaUrl?.let { url ->
                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { openUrl(url) },
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.label_animal_more_info))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}