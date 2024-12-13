package com.example.appfinancialcalculations.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinancialcalculations.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora Financiera", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.ProductCalculations.route) }) {
            Text("Cálculos de Productos")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate(Screen.EmployerCalculations.route) }) {
            Text("Cálculos de Empleador")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate(Screen.EmployeeCalculations.route) }) {
            Text("Cálculos de Empleado")
        }
    }
}