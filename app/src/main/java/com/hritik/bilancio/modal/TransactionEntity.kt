package com.hritik.bilancio.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat


@Entity(tableName = "transactions_table")
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name="amount")
    var amount:Double,

    @ColumnInfo(name="transactionType")
    var transactionType:String,

    @ColumnInfo(name="tag")
    var tag:String,

    @ColumnInfo(name = "date")
    var date:String,

    @ColumnInfo(name="note")
    var note:String,

    @ColumnInfo(name="createdAt")
    var createdAt:Long = System.currentTimeMillis(),

) : Serializable {
    val createdAtDateFormat: String
    get() = DateFormat.getDateTimeInstance()
        .format(createdAt)
}
