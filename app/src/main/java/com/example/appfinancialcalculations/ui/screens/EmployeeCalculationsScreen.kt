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
import com.example.appfinancialcalculations.viewmodel.EmployeeViewModel

@Composable
fun EmployeeCalculationsScreen(employeeViewModel: EmployeeViewModel = viewModel()) {
    var baseSalary by remember { mutableStateOf("") }
    var extraHoursDiurnas by remember { mutableStateOf("") }
    var extraHoursNocturnas by remember { mutableStateOf("") }
    var extraHoursFestivas by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cálculos Empleado",
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
            employeeViewModel.calculateNetSalary(salary)
        }) {
            Text("Calcular Salario Neto")
        }
        Text("Salario Neto: ${employeeViewModel.netSalary.value}")

        EmployeeExtraHoursSection(
            baseSalary = baseSalary,
            extraHours = extraHoursDiurnas,
            onExtraHoursChange = { extraHoursDiurnas = it },
            extraHourName = "Diurna",
            rate = 1.25,
            employeeViewModel = employeeViewModel
        )

        EmployeeExtraHoursSection(
            baseSalary = baseSalary,
            extraHours = extraHoursNocturnas,
            onExtraHoursChange = { extraHoursNocturnas = it },
            extraHourName = "Nocturna",
            rate = 1.75,
            employeeViewModel = employeeViewModel
        )

        EmployeeExtraHoursSection(
            baseSalary = baseSalary,
            extraHours = extraHoursFestivas,
            onExtraHoursChange = { extraHoursFestivas = it },
            extraHourName = "Dominical/Festiva",
            rate = 2.0,
            employeeViewModel = employeeViewModel
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Total pago con horas extras: ${employeeViewModel.totalExtraHoursAndNetSalary}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun EmployeeExtraHoursSection(
    baseSalary: String,
    extraHours: String,
    onExtraHoursChange: (String) -> Unit,
    extraHourName: String,
    rate: Double,
    employeeViewModel: EmployeeViewModel
) {
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = extraHours,
        onValueChange = onExtraHoursChange,
        label = { Text("Horas extras $extraHourName trabajadas") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = {
        val salary = baseSalary.toDoubleOrNull() ?: 0.0
        val hours = extraHours.toIntOrNull() ?: 0
        employeeViewModel.calculateExtraHours(salary, hours, rate, extraHourName)
    }) {
        Text("Calcular Horas Extras $extraHourName")
    }

    val extraPayment = when (extraHourName) {
        "Diurna" -> employeeViewModel.extraHoursDiurnas.value
        "Nocturna" -> employeeViewModel.extraHoursNocturnas.value
        "Dominical/Festiva" -> employeeViewModel.extraHoursFestivas.value
        else -> 0.0
    }

    Text("Pago por horas extras $extraHourName: $extraPayment")
}
