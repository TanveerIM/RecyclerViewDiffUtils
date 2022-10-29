package com.example.recyclerviewdiffutils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdiffutils.databinding.ItemRowBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private lateinit var mBinding: ItemRowBinding

    private val differCallBack = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size


    inner class MyViewHolder : RecyclerView.ViewHolder(mBinding.root){

        fun setData(item: UserModel) {
            mBinding.apply {
                tvId.text = item.id.toString()
                tvName.text = item.userName
            }
        }
    }
}