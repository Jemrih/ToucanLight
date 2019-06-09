package com.toucan.light.toucanlightapplication.storage.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toucan.light.toucanlightapplication.storage.entity.Account
import io.reactivex.Single

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccounts(vararg accounts: Account)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccounts(accounts: List<Account>)
    
    @Delete
    fun deleteAccounts(vararg accounts: Account)

    @Query("SELECT * FROM Accounts")
    fun findAllAccounts(): Single<List<Account>>
}