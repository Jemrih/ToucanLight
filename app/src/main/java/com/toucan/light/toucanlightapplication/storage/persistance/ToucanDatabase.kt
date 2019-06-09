package com.toucan.light.toucanlightapplication.storage.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import com.toucan.light.toucanlightapplication.storage.entity.TransactionHistory
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.LoanDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.TransactionHistoryDao

@Database(
    entities = [
        Account::class,
        Loan::class,
        TransactionHistory::class
    ], version = 4
)
abstract class ToucanDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun loanDao(): LoanDao
    abstract fun transactionHistoryDao(): TransactionHistoryDao
}