package com.hritik.bilancio.ui.addtransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hritik.bilancio.BaseApplication
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.repository.TransactionRepository
import com.hritik.bilancio.utils.AddViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddtransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val app: BaseApplication
) : ViewModel() {
    private val _uiState = MutableStateFlow <AddViewState>(AddViewState.Empty)

    //get ui-state
    val uiState : StateFlow<AddViewState> = _uiState

    val applicationContext = app

    fun addTransaction(transaction : TransactionEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _uiState.value = AddViewState.Loading
             repository.insert(transaction)

        }catch (e : Exception){
            _uiState.value = AddViewState.Error(e)
        }
    }

}