package com.adillasyafira.githubapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adillasyafira.githubapp.activity.DetailUserActivity
import com.adillasyafira.githubapp.databinding.ItemUserBinding
import com.adillasyafira.githubapp.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UsersAdapter (private var itemUsers: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){

    class UserViewHolder (var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = itemUsers[position]
        holder.binding.tvUsername.text = users.login
        holder.binding.tvUrl.text = users.htmlUrl
        Glide.with(holder.itemView.context)
            .load(users.avatarUrl)
            .apply(RequestOptions())
            .circleCrop()
            .into(holder.binding.imgUser)

        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USERNAME, users.login)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = itemUsers.size

}