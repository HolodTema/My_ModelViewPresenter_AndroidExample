package com.terabyte.mvp_androidtrytoescapeactivitylifecycle

import android.content.Context
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database.DatabaseClient
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainView, val allUsers: MutableList<User>) {

    fun onButtonClearWasClicked(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){  DatabaseClient.getInstance(context).userDao().deleteAll()}
            deferred.await()
            allUsers.clear()
            view.updateInterfaceBasedOnNewUsersList(allUsers)
        }
    }

    fun onButtonAddUserWasClicked(context: Context,loginForNewUser: String, passwordForNewUser: String) {
        val newUser = User(0, loginForNewUser, passwordForNewUser)
        allUsers.add(newUser)
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO){
                DatabaseClient.getInstance(context).userDao().insert(newUser)
            }
            deferred.await()
            view.updateInterfaceBasedOnNewUsersList(allUsers)
        }
    }

    companion object{
        private var savedPresenterObject: MainPresenter? = null

        fun createPresenter(context: Context, view: MainView, listener: (mainPresenter: MainPresenter) -> Unit) {
            CoroutineScope(Dispatchers.Main).launch {
                val deferred = async(Dispatchers.IO){
                    DatabaseClient.getInstance(context).userDao().getAll()
                }
                val newMainPresenter = MainPresenter(view, deferred.await())
                listener(newMainPresenter)
            }
        }

        fun savePresenterObject(mainPresenter: MainPresenter) {
            savedPresenterObject = mainPresenter
        }

        fun getPresenterObject() = savedPresenterObject ?: throw NullPresenterException()
    }
}