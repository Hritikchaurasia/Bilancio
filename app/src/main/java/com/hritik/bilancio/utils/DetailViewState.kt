package com.hritik.bilancio.utils

import com.hritik.bilancio.modal.TransactionEntity
import java.lang.Exception

sealed class DetailViewState {
    object Loading : DetailViewState()


    data class Success(val transaction: TransactionEntity) : DetailViewState()
    data class Error(val exception: Exception) : DetailViewState()
}
