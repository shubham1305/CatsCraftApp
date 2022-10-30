package com.example.catscraftapp.catbreeds.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catscraftapp.common.helperclasses.ListFlowPaginator
import com.example.catscraftapp.catbreeds.domain.repository.CatBreedsRepository
import com.example.catscraftapp.catbreeds.presentation.state.CatBreedListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedsViewModel @Inject constructor(
    private val catBreedsRepository: CatBreedsRepository
): ViewModel() {

    var catBreedsListState by mutableStateOf(CatBreedListState())
        private set

    private val breedsListPaginator = ListFlowPaginator(
        initialKey = catBreedsListState.page,
        onLoadUpdated = {
            catBreedsListState = catBreedsListState.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            catBreedsRepository.getCatBreedsList(nextPage, 20)
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
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            breedsListPaginator.loadNextItems()
        }
    }
}