package com.epifiapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun View.isButtonEnable(status : Boolean) {
    if (status) {
        this.apply {
            isEnabled = true
            alpha = 1F
        }
    } else {
        this.apply {
            isEnabled = false
            alpha = 0.7F
        }
    }
}

fun String.isValidPan() : Boolean {

    if (this.length != 10)
        return false

    val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isValidDob(): Boolean {
    val calendar = parseDateString(this)
    val year = calendar[Calendar.YEAR]
    val thisYear = Calendar.getInstance()[Calendar.YEAR]
    return year in 1900 until thisYear
}

fun AppCompatEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}


