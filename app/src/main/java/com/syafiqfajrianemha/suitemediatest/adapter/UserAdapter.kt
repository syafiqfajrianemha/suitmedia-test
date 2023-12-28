package com.syafiqfajrianemha.suitemediatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafiqfajrianemha.suitemediatest.R
import com.syafiqfajrianemha.suitemediatest.data.DataItem
import com.syafiqfajrianemha.suitemediatest.databinding.UserItemBinding

class UserAdapter(private val itemClickCallback: (String) -> Unit) :
    ListAdapter<DataItem, UserAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: UserItemBinding,
        private val itemClickCallback: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                tvFirstname.text = user.firstName
                tvLastname.text = user.lastName
                tvEmail.text = user.email

                Glide.with(root.context)
                    .load(user.avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivAvatarUrl)

                root.setOnClickListener {
                    itemClickCallback.invoke(user.firstName.toString() + " " + user.lastName.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getUser = getItem(position)
        holder.bind(getUser)
    }

    private class DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}