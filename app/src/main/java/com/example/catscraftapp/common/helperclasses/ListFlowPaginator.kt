package com.example.catscraftapp.common.helperclasses

import com.example.catscraftapp.common.interfaces.Paginator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListFlowPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<NetworkResponse<List<Item>>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        coroutineScope {
            if (isMakingRequest) {
                return@coroutineScope
            }
            isMakingRequest = true
            onRequest(currentKey).flowOn(Dispatchers.IO).onEach { result ->
                when (result) {
                    is NetworkResponse.Loading -> onLoadUpdated(true)
                    is NetworkResponse.Success -> {
                        isMakingRequest = false
                        currentKey = getNextKey(result.data!!)
                        onSuccess(result.data, currentKey)
                        onLoadUpdated(false)
                    }
                    is NetworkResponse.Error -> {
                        isMakingRequest = false
                        onError(result.message)
                        onLoadUpdated(false)
                    }
                }
            }.launchIn(this)
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}