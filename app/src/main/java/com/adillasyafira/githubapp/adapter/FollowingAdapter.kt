package com.adillasyafira.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adillasyafira.githubapp.databinding.ItemFollowBinding
import com.adillasyafira.githubapp.databinding.ItemFollowingBinding
import com.adillasyafira.githubapp.model.FollowResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FollowingAdapter(private val itemFollowing: List<FollowResponseItem>) : RecyclerView.Adapter<FollowingAdapter.FollowViewHolder>() {
    class FollowViewHolder (var binding: ItemFollowingBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding = ItemFollowingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val follow = itemFollowing[position]
        holder.binding.tvUsername.text = follow.login
        holder.binding.tvUrl.text = follow.url
        Glide.with(holder.itemView.context)
            .load(follow.avatarUrl)
            .apply(RequestOptions())
            .circleCrop()
            .into(holder.binding.imgUser)
    }

    override fun getItemCount(): Int = itemFollowing.size
}