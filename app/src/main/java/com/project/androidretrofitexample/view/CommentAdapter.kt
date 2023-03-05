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
import com.project.androidretrofitexample.MainActivity.Companion.users
import com.project.androidretrofitexample.model.Users
import com.project.androidretrofitexample.R
import com.project.androidretrofitexample.model.Comment
import java.util.*


class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var comments: List<Comment> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setComments(comments: List<Comment>) {
        //Collections.reverse(comments);
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val comment = comments[position]

        holder.content.text = comment.title
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val content: TextView = itemView.findViewById(R.id.tv_content)
    }

    companion object {
        private val TAG = "CommentAdapter::"
    }
}