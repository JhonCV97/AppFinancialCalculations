package com.example.appfinancialcalculations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ProductViewModel : ViewModel() {
    val priceWithVAT = mutableStateOf(0.0)
    val profitMargin = mutableStateOf(0.0)
    val breakEvenPoint = mutableStateOf(0.0)
    val roi = mutableStateOf(0.0)

    fun calculatePriceWithVAT(basePrice: Double) {
        priceWithVAT.value = basePrice * 1.19
    }

    fun calculateProfitMargin(salePrice: Double, cost: Double) {
        profitMargin.value = ((salePrice - cost) / salePrice) * 100
    }

    fun calculateBreakEvenPoint(fixedCosts: Double, salePrice: Double, variableCost: Double) {
        breakEvenPoint.value = if (salePrice > variableCost) {
            fixedCosts / (salePrice - variableCost)
        } else {
            0.0
        }
    }

    fun calculateROI(income: Double, investment: Double) {
        roi.value = if (investment != 0.0) {
            ((income - investment) / investment) * 100
        } else {
            0.0
        }
    }
}