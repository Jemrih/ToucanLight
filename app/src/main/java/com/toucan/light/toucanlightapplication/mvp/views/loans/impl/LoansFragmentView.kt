package com.toucan.light.toucanlightapplication.mvp.views.loans.impl

import com.toucan.light.toucanlightapplication.storage.entity.Loan

interface LoansFragmentView {
    /** Push loans to UI */
    fun submitLoans(list: List<Loan>)
    /** Show UI notice that user failed to get data from API/DB */
    fun showFailedToGetData()
}