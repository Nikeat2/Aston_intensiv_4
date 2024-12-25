package com.example.astonhomework4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsersAdapter(private val usersList: MutableList<Users>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private lateinit var onClickListener: OnUserClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUserSurname: TextView = view.findViewById(R.id.tvUserSurname)
        val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        val tvUserPhoneNumber: TextView = view.findViewById(R.id.tvUserPhoneNumber)
        val tvUserImage: ImageView = view.findViewById(R.id.ivUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.tvUserSurname.text = user.surname
        holder.tvUserName.text = user.name
        holder.tvUserPhoneNumber.text = user.phoneNumber
        Glide.with(holder.itemView.context).load(user.image).centerCrop().circleCrop()
            .into(holder.tvUserImage)

        holder.itemView.setOnClickListener {
                onClickListener.onUserClick(position)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun updateList(newUsers: List<Users>) {
        val diffCallback = DiffUtilUsers(usersList, newUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        usersList.clear()
        usersList.addAll(newUsers)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnClickListener(listener: OnUserClickListener) {
        onClickListener = listener
    }

    interface OnUserClickListener {
        fun onUserClick(position: Int)
    }
}