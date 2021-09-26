package com.hritik.bilancio.ui.allexpensescreen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hritik.bilancio.R
import com.hritik.bilancio.ui.common.BaseScreen
import com.hritik.bilancio.ui.common.ListTransactionScreen
import com.hritik.bilancio.ui.common.Loading
import com.hritik.bilancio.utils.TransactionTypeViewState


@ExperimentalMaterialApi
@Composable
fun AllexpenseScreen(
    navController: NavHostController,
    viewModel: AllexpenseViewModel = viewModel(),
) {

    val currentUiState = viewModel.uiState.collectAsState()

    currentUiState.value.let { state ->
        when (state) {
            is TransactionTypeViewState.Loading -> {
                Loading()
            }
            is TransactionTypeViewState.Empty -> {
                BaseScreen(navController = navController,
                    displayText = stringResource(R.string.NODATA),
                    title = stringResource(
                        R.string.allexpenses))
            }
            is TransactionTypeViewState.Success -> {
                ListTransactionScreen(navController = navController,
                    transactionList = state.transactions,
                    title = stringResource(
                        R.string.allexpenses))
            }
            is TransactionTypeViewState.Error -> {
                BaseScreen(navController = navController,
                    displayText = stringResource(R.string.ERROROCCUR),
                    title = stringResource(
                        R.string.allexpenses))
            }
        }
    }


}

