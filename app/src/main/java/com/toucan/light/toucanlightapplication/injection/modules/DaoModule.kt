package com.toucan.light.toucanlightapplication.injection.modules

import android.content.Context
import androidx.room.Room
import com.toucan.light.toucanlightapplication.injection.scopes.PerApp
import com.toucan.light.toucanlightapplication.storage.persistance.ToucanDatabase
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.LoanDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.TransactionHistoryDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @PerApp
    @Provides
    internal fun providesDatabase(context: Context): ToucanDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ToucanDatabase::class.java,
            "toucan-light-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @PerApp
    @Provides
    internal fun providesAccountsDao(database: ToucanDatabase): AccountDao = database.accountDao()

    @PerApp
    @Provides
    internal fun providesLoansDao(database: ToucanDatabase): LoanDao = database.loanDao()

    @PerApp
    @Provides
    internal fun providesTransactionHistoriesDao(database: ToucanDatabase): TransactionHistoryDao =
        database.transactionHistoryDao()
}