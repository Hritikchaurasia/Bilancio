package com.hritik.bilancio.ui.allincomescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hritik.bilancio.repository.TransactionRepository
import com.hritik.bilancio.utils.TransactionTypeViewState
import com.hritik.bilancio.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class AllincomeViewModel @Inject constructor(
    val repository: TransactionRepository
) : ViewModel() {

    //initially Loading
    private val _uiState = MutableStateFlow<TransactionTypeViewState>(TransactionTypeViewState.Loading)

    //get ui-state
    val uiState: StateFlow<TransactionTypeViewState> = _uiState


    init {
        getAllIncomeTransaction()
    }

    fun getAllIncomeTransaction() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val allincomeTransactionFlow =
                repository.getAllSingleTransaction(transactionType = "Income").collect { result ->
                    if (result.isNullOrEmpty()) {
                        _uiState.value = TransactionTypeViewState.Empty
                    } else {
                        _uiState.value = TransactionTypeViewState.Success(result)
                    }
                }

        } catch (e: Exception) {
            _uiState.value = TransactionTypeViewState.Error(e)
        }
    }

}