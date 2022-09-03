package com.epifiapp.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epifiapp.room.MyDatabase
import com.epifiapp.room.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private var panNo = ""
    private var date = ""
    private var month = ""
    private var year = ""

    fun getPAN() = panNo
    fun getDate() = date
    fun getMonth() = month
    fun getYear() = year

    val updatePAN: (String) -> Unit = { panNo = it }
    val updateDate: (String) -> Unit = { date = it }
    val updateMonth: (String) -> Unit = { month = it }
    val updateYear: (String) -> Unit = { year = it }

    fun addData(context: Activity) {
        val userDatabase = MyDatabase.getInstance(context).getUserDao()
        val newUser = User(panNo, "$date-$month-$year")
        viewModelScope.launch(Dispatchers.IO) {
            userDatabase.insert(newUser)
        }
    }

}