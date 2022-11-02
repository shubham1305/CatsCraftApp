package com.example.catscraftapp.common

object AppConstants {

    // Base URLS
    const val BASE_URL = "https://api.thecatapi.com/"

    // URL endpoints
    const val ENDPOINT_GET_CAT_BREEDS = "v1/breeds/"

    // Query Params
    const val PARAM_PAGE_NUMBER = "page"
    const val PARAM_PAGE_LIMIT = "limit"

    // IDs and Tags
    const val CAT_BREED_ID = "catBreedId"
    const val CAT_MORE_INFO_TAG = "moreInfoTag"

    // Error messages
    const val ERROR_HTTP_EXCEPTION = "An unexpected error occurred"
    const val ERROR_IO_EXCEPTION = "Couldn't reach server. Pls check internet connection"
}