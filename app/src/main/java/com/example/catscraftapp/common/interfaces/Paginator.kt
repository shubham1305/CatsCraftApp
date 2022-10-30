package com.example.catscraftapp.common.interfaces

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}