package com.toucan.light.toucanlightapplication.utils.extensions

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}

fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.isNotVisible(): Boolean {
    return visibility != View.VISIBLE
}

fun View.isNotInvisible(): Boolean {
    return visibility != View.INVISIBLE
}

fun View.isNotGone(): Boolean {
    return visibility != View.GONE
}

fun View.showElseGone(show: Boolean) {
    if (show) {
        visible()
    } else {
        gone()
    }
}

fun View.showElseInvisible(show: Boolean) {
    if (show) {
        visible()
    } else {
        invisible()
    }
}