package com.hritik.bilancio.repository

import com.hritik.bilancio.database.AppDatabase
import com.hritik.bilancio.modal.TransactionEntity
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val db: AppDatabase) {

    //insert transaction
    suspend fun insert(transaction: TransactionEntity) =
        db.getTransactionDao().insertTransaction(transaction)

    //update transaction
    suspend fun update(transaction: TransactionEntity) =
        db.getTransactionDao().updateTransaction(transaction)

    //delete transaction
    suspend fun delete(transaction: TransactionEntity) =
        db.getTransactionDao().deleteTransaction(transaction)

    //get all transaction
    fun getAllTransactions() = db.getTransactionDao().getAllTransactions();

    //get latest 5 transaction
    fun getLatestTransactions() = db.getTransactionDao().getLatestTransactions()

    //get transaction according to transaction type
    fun getAllSingleTransaction(transactionType: String) = if (transactionType == "Overall") {
        getAllTransactions()
    } else {
        db.getTransactionDao().getTransaction(transactionType)
    }

    //get transaction by id
    suspend fun getByID(id: Int) = db.getTransactionDao().getTransactionByID(id)

    //delete transaction by id
    suspend fun deleteByID(id: Int) = db.getTransactionDao().deleteTransactionByID(id)

    fun  getSumOfTransactionType(transactionType: String) = db.getTransactionDao().getSumOfTransactionType(transactionType)

}