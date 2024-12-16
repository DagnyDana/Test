package com.example.counterapplication

import org.junit.Test
import org.junit.Assert.assertEquals

class TextCounterTest {

    @Test
    fun testCountWords() {
        val text = "Hello world! This is a test."
        val expectedWordCount = 6
        assertEquals(expectedWordCount, TextCounter.countWords(text))
    }

    @Test
    fun testCountWordsWithEmptyInput() {
        val text = ""
        val expectedWordCount = 0
        assertEquals(expectedWordCount, TextCounter.countWords(text))
    }

    @Test
    fun testCountCharacters() {
        val text = "Hello world!"
        val expectedCharCount = 12
        assertEquals(expectedCharCount, TextCounter.countCharacters(text))
    }

    @Test
    fun testCountCharactersWithEmptyInput() {
        val text = ""
        val expectedCharCount = 0
        assertEquals(expectedCharCount, TextCounter.countCharacters(text))
    }
}
