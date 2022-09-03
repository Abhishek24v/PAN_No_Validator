package com.epifiapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.epifiapp.room.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

}