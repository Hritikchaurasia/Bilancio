package com.hritik.bilancio.ui.detailtransaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.repository.TransactionRepository
import com.hritik.bilancio.utils.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class DetailtransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)

    //get ui-state
    val uiState: StateFlow<DetailViewState> = _uiState

    fun getTransactionDetail(transactionID: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val transaction = repository.getByID(transactionID)
            _uiState.value = DetailViewState.Success(transaction = transaction)
        } catch (e: Exception) {
            _uiState.value = DetailViewState.Error(e)
        }
    }

    fun updateTransactionData(transaction: TransactionEntity) = viewModelScope.launch(Dispatchers.IO) {
        _uiState.value = DetailViewState.Loading
        try {
            repository.update(transaction)
            val updatedTransaction: TransactionEntity = repository.getByID(transaction.id)
            _uiState.value = DetailViewState.Success(updatedTransaction)
        } catch (e: Exception) {
            _uiState.value = DetailViewState.Error(e)
        }
    }

    fun deleteTransaction(transactionID: Int) = viewModelScope.launch(Dispatchers.IO) {
        _uiState.value = DetailViewState.Loading
        try {
            repository.deleteByID(transactionID)

        } catch (e: Exception) {
            _uiState.value = DetailViewState.Error(Exception("Can't be deleted"))
        }
    }
}