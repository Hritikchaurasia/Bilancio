package com.hritik.bilancio.ui.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hritik.bilancio.repository.TransactionRepository
import com.hritik.bilancio.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    //initially Loading
    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)

    //get ui-state
    val uiState: StateFlow<ViewState> = _uiState


    init {
        getAllData()
    }


    private fun getAllData() = viewModelScope.launch(Dispatchers.IO) {
        try {



            repository.getLatestTransactions().collect { result ->
                val totalIncome = repository.getSumOfTransactionType("Income")
                val totalExpense = repository.getSumOfTransactionType("Expense")
                val totalBalance: Double = totalIncome - totalExpense
                if (result.isNullOrEmpty()) {
                    _uiState.value = ViewState.Empty
                } else {
                    _uiState.value = ViewState.Success(
                        transactions = result,
                        totalBalance = totalBalance,
                        totalIncome = totalIncome,
                        totalExpense = totalExpense
                    )
                }
            }

        } catch (e: Exception) {
            _uiState.value = ViewState.Error(e)
        }
    }


}
