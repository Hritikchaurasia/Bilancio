package com.hritik.bilancio.modal

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Delete()
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions_table ORDER by createdAt DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    //get single transaction by transaction type
    @Query("SELECT * FROM transactions_table where transactionType == :transactionType ORDER by createdAt DESC")
    fun getTransaction(transactionType: String): Flow<List<TransactionEntity>>

    //get latest 5 transaction
    @Query("SELECT * FROM transactions_table ORDER by createdAt DESC LIMIT 5")
    fun getLatestTransactions(): Flow<List<TransactionEntity>>

    //get transaction by id
    @Query("SELECT * FROM transactions_table where id = :id")
    suspend fun getTransactionByID(id: Int): TransactionEntity

    // delete transaction by id
    @Query("DELETE FROM transactions_table WHERE id = :id")
    suspend fun deleteTransactionByID(id: Int)


    //get income/expense total value
    @Query("SELECT SUM(amount)  FROM transactions_table where transactionType = :transactionType")
    fun getSumOfTransactionType(transactionType: String) : Double

}