package com.example.catscraftapp.catbreeds.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catscraftapp.common.helperclasses.ListFlowPaginator
import com.example.catscraftapp.catbreeds.domain.repository.CatsRepository
import com.example.catscraftapp.catbreeds.presentation.state.CatsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val catsRepository: CatsRepository
): ViewModel() {

    var catBreedsListState by mutableStateOf(CatsListState())
        private set

    private val breedsListPaginator = ListFlowPaginator(
        initialKey = catBreedsListState.page,
        onLoadUpdated = {
            catBreedsListState = catBreedsListState.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            catsRepository.getCatBreedsList(nextPage, 20)
        },
        getNextKey = {
            catBreedsListState.page + 1
        },
        onError = {
            catBreedsListState = catBreedsListState.copy(error = it)
        },
        onSuccess = { items, newKey ->
            catBreedsListState = catBreedsListState.copy(
                breedList = catBreedsListState.breedList + items,
                error = null,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextCatBreeds()
    }

    fun loadNextCatBreeds() {
        viewModelScope.launch {
            breedsListPaginator.loadNextItems()
        }
    }
}