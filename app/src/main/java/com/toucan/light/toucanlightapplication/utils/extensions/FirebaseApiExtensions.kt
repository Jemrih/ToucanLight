package com.toucan.light.toucanlightapplication.utils.extensions

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

fun FirebaseFirestore.getAccountsReference(): Query {
    return collection("Profile").document("User1").collection("Accounts")
}

fun FirebaseFirestore.getLoansReference(): Query {
    return collection("Profile").document("User1").collection("Loans")
}

fun FirebaseFirestore.getTransactionHistoryReference(): Query {
    return collection("Profile")
        .document("User1")
        .collection("Accounts")
        .document("Account1")
        .collection("Transactions")
}