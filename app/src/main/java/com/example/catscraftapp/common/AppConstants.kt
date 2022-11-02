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

    //Test-Tags
    const val LIST_SCREEN_ERROR_VIEW_TEST_TAG_1 = "errorViewTestTag1"
    const val LIST_SCREEN_ERROR_VIEW_TEST_TAG_2 = "errorViewTestTag2"
    const val LIST_SCREEN_ITEM_VIEW_TEST_TAG = "itemViewTestTag"
    const val LIST_SCREEN_LOADING_VIEW_TEST_TAG = "loadingViewTestTag"

    const val DETAIL_SCREEN_ROOT_VIEW_TEST_TAG = "rootViewTestTag"
    const val DETAIL_SCREEN_IMAGE_VIEW_TEST_TAG = "imageViewTestTag"
    const val DETAIL_SCREEN_NAME_VIEW_TEST_TAG = "nameViewTestTag"
    const val DETAIL_SCREEN_DESCRIPTION_VIEW_TEST_TAG = "descriptionViewTestTag"
    const val DETAIL_SCREEN_MORE_DESCRIPTION_VIEW_TEST_TAG = "moreDescriptionViewTestTag"
}