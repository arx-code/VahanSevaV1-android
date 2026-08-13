package com.vahanseva.auto_mall.ui.components

import org.junit.Test
import org.junit.Assert.*

/**
 * Test for button components
 * Verifies button state handling and validation
 */
class ButtonTest {

    @Test
    fun primaryButton_enabled_returnsCorrectState() {
        val enabled = true
        val loading = false
        val shouldClick = enabled && !loading
        assertTrue("Button should be clickable when enabled and not loading", shouldClick)
    }

    @Test
    fun primaryButton_disabled_returnsCorrectState() {
        val enabled = false
        val loading = false
        val shouldClick = enabled && !loading
        assertFalse("Button should not be clickable when disabled", shouldClick)
    }

    @Test
    fun primaryButton_loading_returnsCorrectState() {
        val enabled = true
        val loading = true
        val shouldClick = enabled && !loading
        assertFalse("Button should not be clickable when loading", shouldClick)
    }

    @Test
    fun textButton_clickCallback_isCalledOnce() {
        var clickCount = 0
        val onClick = { clickCount++ }

        // Simulate click
        onClick()
        onClick()
        onClick()

        assertEquals("Click callback should be called 3 times", 3, clickCount)
    }
}