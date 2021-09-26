package com.hritik.bilancio.ui.detailtransaction

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.common.DropDownMenu
import com.hritik.bilancio.ui.common.Loading
import com.hritik.bilancio.utils.DetailViewState

@Composable
fun DetailtransactionScreen(
    navController: NavHostController,
    transactionId: Int,
    viewModel: DetailtransactionViewModel = viewModel(),
) {
    val currentUiState = viewModel.uiState.collectAsState()


    var getData by remember {
        mutableStateOf(true)
    }

    fun updateGetData(input: Boolean) {
        getData = input
    }

    if (getData) {
        viewModel.getTransactionDetail(transactionId)
    }

    currentUiState.value.let { state ->
        Log.d("state", state.toString())
        when (state) {
            is DetailViewState.Loading -> {
                Log.d("update", state.toString())
                Loading()
            }
            is DetailViewState.Success -> {
                SuccessStateDetailtransactionScreen(
                    navController = navController,
                    transaction = state.transaction,
                    viewModel = viewModel,
                    getData = ::updateGetData
                )
            }
            is DetailViewState.Error -> {
                Text(text = state.exception.toString())
            }


        }
    }
}

@Composable
fun SuccessStateDetailtransactionScreen(
    navController: NavHostController,
    transaction: TransactionEntity,
    viewModel: DetailtransactionViewModel,
    getData: (input: Boolean) -> Unit,
) {
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
        mutableStateOf(tagList.indexOf(transaction.tag))
    }
    var selectedTransactionTypeIndex by remember {
        mutableStateOf(transactionTypeList.indexOf(transaction.transactionType))
    }
    var isEditModel by remember {
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf(transaction.title)
    }

    var amount by remember {
        mutableStateOf(transaction.amount)
    }
    var date by remember {
        mutableStateOf(transaction.date)
    }
    var note by remember {
        mutableStateOf(transaction.note)
    }

    fun updateSelectedTagIndex(index: Int) {
        selectedTagIndex = index
    }

    fun updateSelectedTransactionTypeIndex(index: Int) {
        selectedTransactionTypeIndex = index
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isEditModel = !isEditModel

            }) {
                if (isEditModel) {
                    Image(
                        imageVector = Icons.Default.Close,
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "Cancel"
                    )
                } else {
                    Image(
                        imageVector = Icons.Default.Edit,
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "Edit"
                    )
                }
            }
        },

        topBar = {
            Row(
                modifier = Modifier

                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.surface),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.padding(start = 18.dp)
                ) {
                    Text(
                        text = "DETAILS",
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(18.dp)
                    )
                }

                Button(

                    modifier = Modifier
                        .background(color = MaterialTheme.colors.surface)
                        .padding(end = 18.dp),
                    onClick = {
                        if (isEditModel) {

                            viewModel.updateTransactionData(transaction = TransactionEntity(
                                id = transaction.id,
                                title = title,
                                amount = amount,
                                transactionType = transactionTypeList[selectedTransactionTypeIndex],
                                date = date,
                                note = note,
                                tag = tagList[selectedTagIndex]
                            )).run {
                                isEditModel = false
                                getData(true)
                            }

                        } else {
                            getData(false)
                            viewModel.deleteTransaction(transactionID = transaction.id).run {
                                navController.popBackStack()
                            }
                        }
                    }) {
                    if (isEditModel) {
                        Image(
                            imageVector = Icons.Default.Done,
                            contentDescription = "save",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondary)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onError)
                        )
                    }
                }

            }
        },

        content = {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(vertical = 1.dp, horizontal = 10.dp)
                    .background(color = MaterialTheme.colors.background)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = if (isEditModel) Arrangement.SpaceEvenly else Arrangement.Top,
            ) {
                if (isEditModel) {
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
                        label = {
                            Text(
                                text = "Title",
                                color = MaterialTheme.colors.primaryVariant
                            )
                        },

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
                        label = {
                            Text(
                                text = "Amount",
                                color = MaterialTheme.colors.primaryVariant
                            )
                        },

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
                        label = {
                            Text(
                                text = "When",
                                color = MaterialTheme.colors.primaryVariant
                            )
                        },

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
                        transactionTypeList,
                        ::updateSelectedTransactionTypeIndex
                    )
                    DropDownMenu(
                        tagList,
                        ::updateSelectedTagIndex
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
                        label = {
                            Text(
                                text = "Note",
                                color = MaterialTheme.colors.primaryVariant
                            )
                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.primaryVariant,
                            unfocusedBorderColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(20.dp),
                        singleLine = true,
                    )


                } else {

                    Text(
                        text = "TITLE",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.title,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "AMOUNT",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,

                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.amount.toString(),
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Transaction Type",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.transactionType,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "TAG",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.tag,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "When",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.date,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = "NOTE",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.note,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    Text(
                        text = "Created At",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = transaction.createdAtDateFormat,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )



                }

            }
        }

    )

}

