package com.toucan.light.toucanlightapplication.storage.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import com.toucan.light.toucanlightapplication.storage.entity.TransactionHistory
import io.reactivex.Single

@Dao
interface TransactionHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactionHistories(vararg transactionHistory: TransactionHistory)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactionHistories(transactionHistories: List<TransactionHistory>)
    
    @Delete
    fun deleteTransactionHistories(vararg transactionHistory: TransactionHistory)

    @Query("SELECT * FROM Transaction_History")
    fun findAllTransactionHistories(): Single<List<TransactionHistory>>
}