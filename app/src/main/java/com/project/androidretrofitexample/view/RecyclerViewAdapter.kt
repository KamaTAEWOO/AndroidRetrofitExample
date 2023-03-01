package com.project.androidretrofitexample.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.androidretrofitexample.model.Users
import com.project.androidretrofitexample.R


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var users: List<Users> = emptyList()

    fun setUsers(users: List<Users>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val user = users[position]

        holder.userName.text = user.login

        // image load
        Glide.with(holder.userImg)
            .load(user.avaterUrl)
            .centerCrop()
            .into(holder.userImg)

        holder.itemView.setOnClickListener(holder)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val context = itemView.context

        val userImg: ImageView = itemView.findViewById(R.id.userImg)
        val userName: TextView = itemView.findViewById(R.id.userName)

        override fun onClick(p0: View?) {
            val intent = Intent(context, UserInfoActivity::class.java)
            intent.apply {
                this.putExtra("login", userName.text)
            }
            ContextCompat.startActivity(context, intent, null)
        }
    }

    companion object {
        private val TAG = "RecyclerViewAdapter::"
    }
}