package com.adillasyafira.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adillasyafira.githubapp.databinding.ItemFollowBinding
import com.adillasyafira.githubapp.databinding.ItemFollowerBinding
import com.adillasyafira.githubapp.databinding.ItemFollowingBinding
import com.adillasyafira.githubapp.model.FollowResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FollowerAdapter(private val itemFollower: List<FollowResponseItem>) : RecyclerView.Adapter<FollowerAdapter.FollowViewHolder>() {
    class FollowViewHolder (var binding: ItemFollowerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemFollowerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val follow = itemFollower[position]
        holder.binding.tvUsername.text = follow.login
        holder.binding.tvUrl.text = follow.url
        Glide.with(holder.itemView.context)
            .load(follow.avatarUrl)
            .apply(RequestOptions())
            .circleCrop()
            .into(holder.binding.imgUser)
    }

    override fun getItemCount(): Int = itemFollower.size
}