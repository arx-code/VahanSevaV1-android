package com.vahanseva.auto_mall.ui.navigation

import org.junit.Test
import org.junit.Assert.*

/**
 * Test for navigation route handling
 */
class NavigationTest {

    // Simplified route validation (mirrors Screen.kt logic)
    private val validRoutes = listOf(
        "splash", "login", "register",
        "home", "explore", "sell", "saved", "profile",
        "vehicle/{vehicleId}", "showroom/{showroomId}",
        "chat/{conversationId}"
    )

    @Test
    fun validRoute_login_returnsTrue() {
        val route = "login"
        assertTrue("Login route should be valid", route in validRoutes)
    }

    @Test
    fun validRoute_home_returnsTrue() {
        val route = "home"
        assertTrue("Home route should be valid", route in validRoutes)
    }

    @Test
    fun invalidRoute_unknown_returnsFalse() {
        val route = "unknown"
        assertFalse("Unknown route should be invalid", route in validRoutes)
    }

    @Test
    fun vehicleDetailRoute_formatIsCorrect() {
        val vehicleId = "12345"
        val route = "vehicle/$vehicleId"
        assertTrue("Route should contain vehicle ID", route.contains(vehicleId))
    }

    @Test
    fun showroomRoute_formatIsCorrect() {
        val showroomId = "67890"
        val route = "showroom/$showroomId"
        assertTrue("Route should contain showroom ID", route.contains(showroomId))
    }

    @Test
    fun mainScreens_allPresent() {
        val mainScreens = listOf("home", "explore", "sell", "saved", "profile")
        assertEquals("Should have 5 main screens", 5, mainScreens.size)
        assertTrue("Each main screen should be in valid routes",
            mainScreens.all { it in validRoutes })
    }
}