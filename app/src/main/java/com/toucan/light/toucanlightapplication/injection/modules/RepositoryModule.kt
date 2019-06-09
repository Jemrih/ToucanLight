package com.toucan.light.toucanlightapplication.injection.modules

import com.toucan.light.toucanlightapplication.injection.scopes.PerApp
import com.toucan.light.toucanlightapplication.repository.AccountRepository
import com.toucan.light.toucanlightapplication.repository.LoanRepository
import com.toucan.light.toucanlightapplication.repository.TransactionHistoryRepository
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.LoanDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.TransactionHistoryDao
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @PerApp
    @Provides
    internal fun providesAccountsRepository(dao: AccountDao): AccountRepository {
        return AccountRepository(dao)
    }

    @PerApp
    @Provides
    internal fun providesLoansRepository(dao: LoanDao): LoanRepository {
        return LoanRepository(dao)
    }

    @PerApp
    @Provides
    internal fun providesTransactionHistoriesRepository(dao: TransactionHistoryDao): TransactionHistoryRepository {
        return TransactionHistoryRepository(dao)
    }
}