package com.example.appfinancialcalculations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class EmployerViewModel : ViewModel() {
    val totalPayrollCost = mutableStateOf(0.0)
    val parafiscalContributions = mutableStateOf(0.0)
    val socialSecurity = mutableStateOf(0.0)
    val socialProvisions = mutableStateOf(0.0)

    fun calculateEmployerCosts(baseSalary: Double) {
        parafiscalContributions.value = baseSalary * 0.09
        socialSecurity.value = baseSalary * 0.205
        socialProvisions.value = baseSalary * 0.2183
        totalPayrollCost.value = baseSalary + parafiscalContributions.value +
                socialSecurity.value + socialProvisions.value
    }
}
