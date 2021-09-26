package com.hritik.bilancio.ui.addtransaction

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.common.DropDownMenu
import com.hritik.bilancio.ui.common.Loading
import com.hritik.bilancio.ui.homescreen.Header
import com.hritik.bilancio.utils.AddViewState


@Composable
fun AddTransactionScreen(
    navController: NavHostController,
    viewModel: AddtransactionViewModel = viewModel(),
) {
    val currentUiState = viewModel.uiState.collectAsState()

    currentUiState.value.let { state ->
        when (state) {
            is AddViewState.Loading -> {
                Loading()
            }
            is AddViewState.Empty -> {
                AddViewSuccessState(viewModel = viewModel, navController = navController)
            }
            is AddViewState.Error -> {

            }

        }
    }
}

@Composable
fun AddViewSuccessState(viewModel: AddtransactionViewModel, navController: NavHostController) {

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {

            IconButton(onClick = {}) {
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow",

                    )
            }
            Header("ADD TRANSACTION")
        },
        content = {
            Form(viewModel = viewModel, navController = navController)
        }
    )
}

@Composable
fun Form(viewModel: AddtransactionViewModel, navController: NavHostController) { 
    val tagList: List<String> = listOf("Housing",
        "Entertainment",
        "Food",
        "Insurance",
        "Medical",
        "Personal",
        "Saving",
        "Transport",
        "Utilities",
        "Others")
    val transactionTypeList = listOf("Income", "Expense")

    var selectedTagIndex by remember {
        mutableStateOf(0)
    }
    var selectedTransactionTypeIndex by remember {
        mutableStateOf(0)
    }
    var title by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf(0.0)
    }
    var date by remember {
        mutableStateOf("")
    }
    var note by remember {
        mutableStateOf("")
    }

    fun updateSelectedTagIndex(index: Int) {
        selectedTagIndex = index
    }

    fun updateSelectedTransactionTypeIndex(index: Int) {
        selectedTransactionTypeIndex = index
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            value = title,
            onValueChange = {
                title = it
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primaryVariant
            ),
            label = { Text(text = "Title", color = MaterialTheme.colors.primaryVariant) },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            value = amount.toString(),
            onValueChange = { newAmount ->
                amount = newAmount.toDouble()
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primaryVariant
            ),
            label = { Text(text = "Amount", color = MaterialTheme.colors.primaryVariant) },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,

            )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            value = date, onValueChange = {
                date = it
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primaryVariant
            ),
            label = { Text(text = "When", color = MaterialTheme.colors.primaryVariant) },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        )
        DropDownMenu(
            tagList,
            ::updateSelectedTagIndex
        )
        DropDownMenu(
            transactionTypeList,
            ::updateSelectedTransactionTypeIndex
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            value = note,
            onValueChange = {
                note = it
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primaryVariant
            ),
            label = { Text(text = "Note", color = MaterialTheme.colors.primaryVariant) },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
        )


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
            content = {
                Button(
                    onClick = {
                        viewModel.addTransaction(
                            TransactionEntity(
                                title = title,
                                date = date,
                                amount = amount, note = note,
                                tag = tagList[selectedTagIndex],
                                transactionType = transactionTypeList[selectedTransactionTypeIndex]
                            )
                        ).run {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    )
                ) {
                    Text(
                        text = "Add data",
                        color = Color.White,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        )
    }


}


