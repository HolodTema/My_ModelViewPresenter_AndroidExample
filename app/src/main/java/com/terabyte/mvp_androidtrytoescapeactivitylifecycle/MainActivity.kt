package com.terabyte.mvp_androidtrytoescapeactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database.User
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var wasFirstLaunchHere = false
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!wasFirstLaunchHere) {
            wasFirstLaunchHere = true
            MainPresenter.createPresenter(applicationContext, this) {
                MainPresenter.savePresenterObject(it)
                doInOnCreateWhenWeInitialedPresenter(it, binding)
            }
        }
        else {
            doInOnCreateWhenWeInitialedPresenter(MainPresenter.getPresenterObject(), binding)
        }
    }

    private fun doInOnCreateWhenWeInitialedPresenter(mainPresenter: MainPresenter, binding: ActivityMainBinding) {
        adapter = UserAdapter(layoutInflater, mainPresenter.allUsers)
        binding.recyclerUsers.adapter = adapter
        binding.recyclerUsers.layoutManager = LinearLayoutManager(applicationContext)

        binding.buttonClear.setOnClickListener {
            mainPresenter?.onButtonClearWasClicked(applicationContext)
        }

        binding.buttonAddUser.setOnClickListener {
            mainPresenter?.onButtonAddUserWasClicked(applicationContext, binding.editLogin.text.toString(), binding.editPassword.text.toString())
        }
    }

    override fun updateInterfaceBasedOnNewUsersList(users: MutableList<User>) {
        adapter?.users = users //actually here we can code case if users is empty
        adapter?.notifyDataSetChanged()
    }
}