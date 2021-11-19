package com.mregtej.randomusers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mregtej.randomusers.R
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.databinding.ItemListViewBinding

class MainAdapter(
    private val userClickAction: (String) -> Unit
) : ListAdapter<RandomUserListData, MainAdapter.DataViewHolder>(DataDiffCallback) {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemListViewBinding.bind(itemView)

        fun bind(
            item: RandomUserListData,
            userClickAction: (String) -> Unit
        ) = itemView.apply {
            loadProfilePhoto(item.photoUrl)
            binding.profileName.text = item.name
            binding.profileEmail.text = item.email
            binding.profilePhone.text = item.phone
            setOnClickListener {
                userClickAction(item.uuid)
            }
        }
        
        private fun loadProfilePhoto(url: String) = itemView.apply {
            Glide.with(context).load(url).into(binding.profilePhoto)
        }
    }

    object DataDiffCallback : DiffUtil.ItemCallback<RandomUserListData>() {
        override fun areItemsTheSame(
            oldItem: RandomUserListData,
            newItem: RandomUserListData
        ) = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: RandomUserListData,
            newItem: RandomUserListData
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_view,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position), userClickAction)
    }
}