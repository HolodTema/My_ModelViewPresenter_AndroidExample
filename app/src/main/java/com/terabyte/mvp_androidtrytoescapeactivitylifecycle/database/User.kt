package com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var login: String,
    var password: String
)
