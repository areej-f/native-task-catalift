package com.example.catalift

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalift.screens.EducationDetailsScreen
import com.example.catalift.screens.InterestsScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "education") {
        composable("education") {
            EducationDetailsScreen(
                onContinue = { navController.navigate("interests") },
                onBack = { /* Handle back or exit */ }
            )
        }
        composable("interests") {
            InterestsScreen(
                onContinue = { /* Navigate to next screen or finish */ },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
