package com.hritik.bilancio.utils

import com.hritik.bilancio.modal.TransactionEntity
import java.lang.Exception


sealed class TransactionTypeViewState {

    object Empty : TransactionTypeViewState()
    object Loading : TransactionTypeViewState()

    data class Success(
        val transactions: List<TransactionEntity>
        ) : TransactionTypeViewState()

    data class Error(val exception: Exception) : TransactionTypeViewState()

}