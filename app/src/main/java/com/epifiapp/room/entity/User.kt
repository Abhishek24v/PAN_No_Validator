package com.epifiapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey()
    val panNo: String,
    var dob : String
        )