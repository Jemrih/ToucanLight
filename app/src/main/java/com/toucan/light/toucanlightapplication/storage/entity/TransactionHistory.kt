package com.toucan.light.toucanlightapplication.storage.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "Transaction_History")
@TypeConverters(DateConverter::class)
/** Cant be a dataclass because of firebase/firestore */
class TransactionHistory {
    @PrimaryKey
    @NonNull
    var transactionId: String = ""
    @NonNull
    var title: String = ""
    @NonNull
    var description: String = ""
    @NonNull
    var amount: String = ""
    @NonNull
    var transactionDate: Date = Date()
}

/** Converter that takes date to something more readable for Room */
class DateConverter {
    @TypeConverter
    fun convertFromStringToDate(dateLong: Long): Date {
        val date = Date()
        date.time = dateLong
        return date
    }

    @TypeConverter
    fun convertFromDateToString(date: Date?): Long {
        return date!!.time
    }
}