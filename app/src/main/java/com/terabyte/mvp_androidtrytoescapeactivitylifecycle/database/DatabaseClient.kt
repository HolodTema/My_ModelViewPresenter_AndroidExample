package com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database

import android.content.Context
import androidx.room.Room

object DatabaseClient {
    private var instance: AppDatabase? = null
    fun getInstance(context: Context) = instance ?: Room.databaseBuilder(context, AppDatabase::class.java, Const.DB_NAME).build()
}