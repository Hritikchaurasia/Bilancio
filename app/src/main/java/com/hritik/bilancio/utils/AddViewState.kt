package com.hritik.bilancio.utils

import java.lang.Exception


sealed class AddViewState {
    object Empty : AddViewState()
    object Loading : AddViewState()
    data class Error(val exception: Exception) : AddViewState()
}
