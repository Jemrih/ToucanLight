package com.toucan.light.toucanlightapplication.storage.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
/** Cant be a dataclass because of firebase/firestore */
class Account {
    @PrimaryKey
    @NonNull
    var accountNumber: String = ""
    @NonNull
    var title: String = ""
    @NonNull
    var amount: String = ""
}