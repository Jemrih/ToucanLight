package com.toucan.light.toucanlightapplication.storage.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import io.reactivex.Single

@Dao
interface LoanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoans(vararg loan: Loan)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoans(loans: List<Loan>)
    
    @Delete
    fun deleteLoans(vararg loan: Loan)

    @Query("SELECT * FROM Loans")
    fun findAllLoans(): Single<List<Loan>>
}