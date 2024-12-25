package com.example.astonhomework4

import androidx.recyclerview.widget.DiffUtil

class DiffUtilUsers(private val oldList: List<Users>, private val newList: List<Users>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition] &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].surname == newList[newItemPosition].surname &&
                oldList[oldItemPosition].phoneNumber == newList[newItemPosition].phoneNumber
    }
}