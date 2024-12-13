package com.example.appfinancialcalculations.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfinancialcalculations.viewmodel.EmployerViewModel

@Composable
fun EmployerCalculationsScreen(employerViewModel: EmployerViewModel = viewModel()) {
    var baseSalary by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cálculos Empleador",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = baseSalary,
            onValueChange = { baseSalary = it },
            label = { Text("Salario base") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val salary = baseSalary.toDoubleOrNull() ?: 0.0
            employerViewModel.calculateEmployerCosts(salary)
        }) {
            Text("Calcular Costos")
        }

        Text("Aportes parafiscales: ${employerViewModel.parafiscalContributions.value}")
        Text("Seguridad social: ${employerViewModel.socialSecurity.value}")
        Text("Prestaciones sociales: ${employerViewModel.socialProvisions.value}")
        Text("Costo total nómina: ${employerViewModel.totalPayrollCost.value}")
    }
}
