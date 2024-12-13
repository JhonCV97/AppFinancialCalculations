package com.example.appfinancialcalculations.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appfinancialcalculations.ui.screens.EmployeeCalculationsScreen
import com.example.appfinancialcalculations.ui.screens.EmployerCalculationsScreen
import com.example.appfinancialcalculations.ui.screens.HomeScreen
import com.example.appfinancialcalculations.ui.screens.ProductCalculationsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ProductCalculations : Screen("product_calculations")
    object EmployerCalculations : Screen("employer_calculations")
    object EmployeeCalculations : Screen("employee_calculations")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.ProductCalculations.route) { ProductCalculationsScreen() }
        composable(Screen.EmployerCalculations.route) { EmployerCalculationsScreen() }
        composable(Screen.EmployeeCalculations.route) { EmployeeCalculationsScreen() }
    }
}