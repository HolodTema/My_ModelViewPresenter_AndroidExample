package com.terabyte.mvp_androidtrytoescapeactivitylifecycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.database.User
import com.terabyte.mvp_androidtrytoescapeactivitylifecycle.databinding.RecyclerElemntUserBinding

class UserAdapter(private val inflater: LayoutInflater, var users: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserHolder(RecyclerElemntUserBinding.inflate(inflater))

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.binding.textUserLogin.text = users[position].login
        holder.binding.textUserPassword.text = users[position].password
    }

    override fun getItemCount() = users.size


    class UserHolder(val binding: RecyclerElemntUserBinding): RecyclerView.ViewHolder(binding.root)
}