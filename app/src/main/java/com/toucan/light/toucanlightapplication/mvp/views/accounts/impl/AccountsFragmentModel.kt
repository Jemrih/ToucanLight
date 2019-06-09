package com.toucan.light.toucanlightapplication.mvp.views.accounts.impl

import com.toucan.light.toucanlightapplication.repository.AccountRepository
import com.toucan.light.toucanlightapplication.storage.entity.Account
import io.reactivex.Observable

class AccountsFragmentModel(private val repository: AccountRepository) {
    fun getAccounts(): Observable<List<Account>> {
        return repository.getAccounts()
    }
}