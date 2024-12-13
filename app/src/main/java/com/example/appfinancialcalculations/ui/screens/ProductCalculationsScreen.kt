package com.example.appfinancialcalculations.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfinancialcalculations.viewmodel.ProductViewModel

@Composable
fun ProductCalculationsScreen(productViewModel: ProductViewModel = viewModel()) {
    var basePrice by remember { mutableStateOf("") }
    var salePrice by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var fixedCosts by remember { mutableStateOf("") }
    var variableCost by remember { mutableStateOf("") }
    var income by remember { mutableStateOf("") }
    var investment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cálculos Productos",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Cálculo de Precio con IVA
        TextField(
            value = basePrice,
            onValueChange = { basePrice = it },
            label = { Text("Precio base") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val price = basePrice.toDoubleOrNull() ?: 0.0
            productViewModel.calculatePriceWithVAT(price)
        }) {
            Text("Calcular Precio con IVA")
        }
        Text("Precio con IVA: ${productViewModel.priceWithVAT.value}")

        Spacer(modifier = Modifier.height(16.dp))

        // Cálculo de Margen de Ganancia
        TextField(
            value = salePrice,
            onValueChange = { salePrice = it },
            label = { Text("Precio de venta") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = cost,
            onValueChange = { cost = it },
            label = { Text("Costo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val sale = salePrice.toDoubleOrNull() ?: 0.0
            val costValue = cost.toDoubleOrNull() ?: 0.0
            productViewModel.calculateProfitMargin(sale, costValue)
        }) {
            Text("Calcular Margen de Ganancia")
        }
        Text("Margen de Ganancia: ${productViewModel.profitMargin.value}%")

        Spacer(modifier = Modifier.height(16.dp))

        // Cálculo de Punto de Equilibrio
        TextField(
            value = fixedCosts,
            onValueChange = { fixedCosts = it },
            label = { Text("Costos Fijos") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = variableCost,
            onValueChange = { variableCost = it },
            label = { Text("Costo Variable Unitario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val fixed = fixedCosts.toDoubleOrNull() ?: 0.0
            val sale = salePrice.toDoubleOrNull() ?: 0.0
            val variable = variableCost.toDoubleOrNull() ?: 0.0
            productViewModel.calculateBreakEvenPoint(fixed, sale, variable)
        }) {
            Text("Calcular Punto de Equilibrio")
        }
        Text("Punto de Equilibrio: ${productViewModel.breakEvenPoint.value} unidades")

        Spacer(modifier = Modifier.height(16.dp))

        // Cálculo de ROI
        TextField(
            value = income,
            onValueChange = { income = it },
            label = { Text("Ingresos") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = investment,
            onValueChange = { investment = it },
            label = { Text("Inversión") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val incomeValue = income.toDoubleOrNull() ?: 0.0
            val investmentValue = investment.toDoubleOrNull() ?: 0.0
            productViewModel.calculateROI(incomeValue, investmentValue)
        }) {
            Text("Calcular ROI")
        }
        Text("ROI: ${productViewModel.roi.value}%")
    }
}