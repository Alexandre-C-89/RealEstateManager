package com.example.realestatemanager.feature.lend

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.realestatemanager.feature.lend.model.LendUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LendViewModel @Inject constructor(

) : ViewModel() {

    private val _data = MutableStateFlow(LendUiData())
    val data: StateFlow<LendUiData> = _data

    fun onTotalLoanAmountChanged(value: TextFieldValue) {
        _data.value = data.value.copy(totalLoanAmount = value)
    }

    fun onContributionChanged(value: TextFieldValue) {
        _data.value = data.value.copy(contribution = value)
    }

    fun onRateChanged(value: TextFieldValue) {
        _data.value = data.value.copy(rate = value)
    }

    fun onDurationChanged(value: TextFieldValue) {
        _data.value = data.value.copy(duration = value)
    }

    fun calculateLoan() {
        val totalLoanAmount = _data.value.totalLoanAmount.text.toDoubleOrNull() ?: 0.0
        val contribution = _data.value.contribution.text.toDoubleOrNull() ?: 0.0
        val loanAmount = totalLoanAmount - contribution
        val rateAnnual = _data.value.rate.text.toDoubleOrNull() ?: 0.0
        val durationYears = _data.value.duration.text.toIntOrNull() ?: 0

        if (loanAmount > 0 && rateAnnual > 0 && durationYears > 0) {
            val rateMonthly = (rateAnnual / 100) / 12
            val durationMonths = durationYears * 12
            // Calcul du paiement mensuel
            val monthlyPayment = (loanAmount * rateMonthly) / (1 - Math.pow(1 + rateMonthly, -durationMonths.toDouble()))
            val result = String.format("Monthly Payment: %.2f", monthlyPayment, " €")
            onResultChanged(result)
        } else {
            onResultChanged("Invalid input values")
        }
    }

    private fun onResultChanged(value: String) {
        _data.value = _data.value.copy(result = value)
    }

}