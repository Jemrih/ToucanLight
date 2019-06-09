package com.toucan.light.toucanlightapplication.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.format(pattern: String): String? {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}