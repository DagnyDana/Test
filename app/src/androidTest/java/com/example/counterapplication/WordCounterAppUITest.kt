package com.example.counterapplication

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class WordCounterAppUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWordCounterWithWordsOption() {
        composeTestRule.setContent {
            WordCounterApp()
        }

        // Enter text into the input field
        composeTestRule.onNodeWithText("Enter text").performTextInput("Hello world!")

        // Select "Words" from the dropdown
        composeTestRule.onNodeWithText("Words").performClick()

        // Click the "Count" button
        composeTestRule.onNodeWithText("Count").performClick()

        // Verify the result
        composeTestRule.onNodeWithText("Word Count: 2").assertExists()
    }

    @Test
    fun testWordCounterWithEmptyInput() {
        composeTestRule.setContent {
            WordCounterApp()
        }

        // Click the "Count" button without entering text
        composeTestRule.onNodeWithText("Count").performClick()

        // Verify the Toast message appears
        // Unfortunately, you cannot directly test Toast messages with Compose testing.
        // For verification, use manual testing or mock `Toast.makeText` in your code.
    }

    @Test
    fun testCharacterCounterOption() {
        composeTestRule.setContent {
            WordCounterApp()
        }

        // Enter text into the input field
        composeTestRule.onNodeWithText("Enter text").performTextInput("Compose UI Test")

        // Select "Characters" from the dropdown
        composeTestRule.onNodeWithText("Words").performClick()
        composeTestRule.onNodeWithText("Characters").performClick()

        // Click the "Count" button
        composeTestRule.onNodeWithText("Count").performClick()

        // Verify the result
        composeTestRule.onNodeWithText("Character Count: 15").assertExists()
    }
}
