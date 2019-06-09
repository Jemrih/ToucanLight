package com.toucan.light.toucanlightapplication.mvp.views.accounts.impl

import com.toucan.light.toucanlightapplication.storage.entity.Account

interface AccountsFragmentView {
    /** Push accounts to UI */
    fun submitAccounts(accounts: List<Account>)
    /** Show UI notice that user failed to get data from API/DB */
    fun showFailedToGetData()

    fun openAccountHistoryView(account: Account)
}