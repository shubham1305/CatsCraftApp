package com.example.catscraftapp.catbreeds.presentation.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.catscraftapp.catbreeds.data.remote.dto.toCatBreed
import com.example.catscraftapp.catbreeds.domain.model.CatBreed
import com.example.catscraftapp.common.AppConstants
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatBreedDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testImageLoadScenario() {
        val state = mutableStateOf(getRandomCatBreed())

        composeTestRule.setContent {
            CatBreedDetailScreen(
                modifier = Modifier.fillMaxWidth(),
                catBreed = state.value
            ) {}
        }

        composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_IMAGE_VIEW_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_NAME_VIEW_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_DESCRIPTION_VIEW_TEST_TAG).assertIsDisplayed()
        state.value.altNames?.let {
            composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_NAME_VIEW_TEST_TAG).assertTextContains(it, substring = true, ignoreCase = true)
        }
        composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_ROOT_VIEW_TEST_TAG).performScrollToNode(
            SemanticsMatcher.expectValue(SemanticsProperties.TestTag, AppConstants.DETAIL_SCREEN_MORE_DESCRIPTION_VIEW_TEST_TAG))
        state.value.wikipediaUrl?.let {
            composeTestRule.onNodeWithTag(AppConstants.DETAIL_SCREEN_MORE_DESCRIPTION_VIEW_TEST_TAG).assertIsDisplayed().performClick()
        }
    }

    private fun getJson(path: String): String {
        val file = InstrumentationRegistry.getInstrumentation().context.resources.assets.open(path)
        return String(file.readBytes())
    }

    private fun getRandomCatBreed(): CatBreed {
        return Gson().fromJson(
            getJson("test.json"),
            CatBreedDtoList::class.java
        ).random().toCatBreed()
    }
}


