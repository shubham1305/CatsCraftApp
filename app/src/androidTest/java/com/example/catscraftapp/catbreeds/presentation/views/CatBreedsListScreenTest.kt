package com.example.catscraftapp.catbreeds.presentation.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.catscraftapp.catbreeds.data.remote.dto.CatBreedDto
import com.example.catscraftapp.catbreeds.data.remote.dto.toCatBreed
import com.example.catscraftapp.catbreeds.presentation.state.CatsListState
import com.example.catscraftapp.common.AppConstants
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatBreedsListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAllErrorScenarios() {
        val state = mutableStateOf(getTestCatsListState())

        composeTestRule.setContent {
            CatBreedsListScreen(
                modifier = Modifier.fillMaxWidth(),
                catsListState = state.value,
                navController = rememberNavController()
            ) {}
        }


        // Test if error occured message is visible
        state.value = state.value.copy(error = "Some error occured", breedList = emptyList(), isLoading = false)
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ERROR_VIEW_TEST_TAG_1).assertIsDisplayed()
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ERROR_VIEW_TEST_TAG_1).assertTextContains("Some error occured")
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_LOADING_VIEW_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ITEM_VIEW_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun testAllLoadingScenarios() {
        val state = mutableStateOf(getTestCatsListState())

        composeTestRule.setContent {
            CatBreedsListScreen(
                modifier = Modifier.fillMaxWidth(),
                catsListState = state.value,
                navController = rememberNavController()
            ) {}
        }

        // Test if state is loading
        state.value = state.value.copy(error = null, breedList = emptyList(), isLoading = true)
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_LOADING_VIEW_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ERROR_VIEW_TEST_TAG_1).assertDoesNotExist()
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ITEM_VIEW_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun testAllPositiveScenarios() {
        val state = mutableStateOf(getTestCatsListState())

        composeTestRule.setContent {
            CatBreedsListScreen(
                modifier = Modifier.fillMaxWidth(),
                catsListState = state.value,
                navController = rememberNavController()
            ) {}
        }

        // Test if state is successful items are displayed
        state.value = state.value.copy(error = null, isLoading = false, breedList = getTestCatsListState().breedList)
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_LOADING_VIEW_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(AppConstants.LIST_SCREEN_ERROR_VIEW_TEST_TAG_1).assertDoesNotExist()
        composeTestRule.onNodeWithTag("${AppConstants.LIST_SCREEN_ITEM_VIEW_TEST_TAG}1").assertIsDisplayed()
//        composeTestRule.onNodeWithTag("${AppConstants.LIST_SCREEN_ITEM_VIEW_TEST_TAG}1").performClick()
    }

    private fun getJson(path: String): String {
        val file = InstrumentationRegistry.getInstrumentation().context.resources.assets.open(path)
        return String(file.readBytes())
    }

    private fun getTestCatsListState(): CatsListState {
        return CatsListState(
            isLoading = true,
            breedList = Gson().fromJson(
                getJson("test.json"),
                CatBreedDtoList::class.java
            ).map { it.toCatBreed() },
            error = null,
            endReached = true,
            page = 1
        )
    }
}

class CatBreedDtoList: ArrayList<CatBreedDto>()



