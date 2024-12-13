package com.example.appfinancialcalculations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class EmployeeViewModel : ViewModel() {
    val netSalary = mutableStateOf(0.0)
    val extraHoursDiurnas = mutableStateOf(0.0)
    val extraHoursNocturnas = mutableStateOf(0.0)
    val extraHoursFestivas = mutableStateOf(0.0)

    fun calculateNetSalary(baseSalary: Double) {
        netSalary.value = baseSalary - (baseSalary * 0.04 + baseSalary * 0.04)
    }

    fun calculateExtraHours(baseSalary: Double, hours: Int, rate: Double, type: String) {
        val extraPayment = (baseSalary / 240) * rate * hours
        when (type) {
            "Diurna" -> extraHoursDiurnas.value = extraPayment
            "Nocturna" -> extraHoursNocturnas.value = extraPayment
            "Dominical/Festiva" -> extraHoursFestivas.value = extraPayment
        }
    }

    val totalExtraHoursAndNetSalary: Double
        get() = extraHoursDiurnas.value + extraHoursNocturnas.value + extraHoursFestivas.value + netSalary.value
}