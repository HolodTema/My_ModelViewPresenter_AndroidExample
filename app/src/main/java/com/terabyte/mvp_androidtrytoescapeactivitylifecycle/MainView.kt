package com.terabyte.mvp_androidtrytoescapeactivitylifecycle

import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database.User

interface MainView {
    fun updateInterfaceBasedOnNewUsersList(users: MutableList<User>)
}