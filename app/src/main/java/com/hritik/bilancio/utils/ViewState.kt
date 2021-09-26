package com.hritik.bilancio.utils

import com.hritik.bilancio.modal.TransactionEntity
import java.lang.Exception

sealed class ViewState {
    object Empty : ViewState()
    object Loading : ViewState()

    data class Success(
        val transactions: List<TransactionEntity>,
        val totalBalance: Double,
        val totalIncome: Double,
        val totalExpense: Double
    ) : ViewState()

    data class Error(val exception: Exception) : ViewState()
}
