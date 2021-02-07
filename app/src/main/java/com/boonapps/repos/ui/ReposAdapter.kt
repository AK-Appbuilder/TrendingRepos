package com.boonapps.repos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.boonapps.repos.models.Repo

class ReposAdapter : ListAdapter<Repo, ReposAdapter.ViewHolder>(VoucherDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item, isUnlocked, morePoints, progress)

        holder.binding.redeem.setOnClickListener {
            listener(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        val binding: ItemRewardsVoucherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Repo,
            isUnLocked: Boolean,
            morePoints: Long,
            progress: Int
        ) {
            binding.voucher = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRewardsVoucherBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}

class VoucherDiffCallback : DiffUtil.ItemCallback<RewardsVoucher>() {
    override fun areItemsTheSame(oldItem: RewardsVoucher, newItem: RewardsVoucher): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RewardsVoucher, newItem: RewardsVoucher): Boolean {
        return oldItem == newItem
    }
}