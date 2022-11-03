package com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
    @Update
    fun update(user: User)
    @Delete
    fun delete(user: User)
    @Query("select * from user")
    fun getAll(): MutableList<User>
    @Query("delete from user")
    fun deleteAll()
}