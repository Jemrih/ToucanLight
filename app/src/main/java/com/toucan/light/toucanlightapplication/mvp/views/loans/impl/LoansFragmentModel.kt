package com.toucan.light.toucanlightapplication.mvp.views.loans.impl

import com.toucan.light.toucanlightapplication.repository.LoanRepository
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import io.reactivex.Observable

class LoansFragmentModel(private val loansRepository: LoanRepository) {
    fun getLoans(): Observable<List<Loan>> = loansRepository.getLoans()
}