package com.example.catscraftapp.catbreeds.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catscraftapp.R
import com.example.catscraftapp.catbreeds.presentation.CatBreedsViewModel
import com.example.catscraftapp.catbreeds.presentation.views.components.CustomImageView
import com.example.catscraftapp.catbreeds.presentation.views.components.ItemPropertyView
import com.example.catscraftapp.common.AppConstants

@Composable
fun CatBreedDetailScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: CatBreedsViewModel,
    openUrl: (String) -> Unit
) {
    val breedId =
        navController.currentBackStackEntry?.arguments?.get(AppConstants.CAT_BREED_ID).toString()
    val catBreed = remember {
        viewModel.catBreedsListState.breedList.firstOrNull { it.id == breedId }
    }

    catBreed?.let {
        val linkColor = MaterialTheme.colors.primaryVariant
        val descriptionString = stringResource(id = R.string.label_animal_more_info, it.description)

        val description = remember {
            buildAnnotatedString {
                append(descriptionString)
                pushStringAnnotation(tag = AppConstants.CAT_MORE_INFO_TAG, it.wikipediaUrl)
                withStyle(style = SpanStyle(linkColor)) { append(" here") }
                pop()
            }
        }

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
                imageUrl = it.image?.url,
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
                        value = stringResource(id = R.string.value_animal_weight, it.weight.metric)
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

                    ClickableText(text = description) { offset ->
                        description.getStringAnnotations(
                            tag = AppConstants.CAT_MORE_INFO_TAG,
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            openUrl(it.item)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}