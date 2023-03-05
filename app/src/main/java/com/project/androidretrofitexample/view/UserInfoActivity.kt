package com.project.androidretrofitexample.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.amitshekhar.DebugDB
import com.bumptech.glide.Glide
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.MainActivity.Companion.users
import com.project.androidretrofitexample.R
import com.project.androidretrofitexample.database.CommentDatabase
import com.project.androidretrofitexample.databinding.ActivityUserInfoBinding
import com.project.androidretrofitexample.model.Comment
import com.project.androidretrofitexample.model.User
import com.project.androidretrofitexample.viewModel.CommentViewModel
import com.project.androidretrofitexample.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var db: CommentDatabase

    private lateinit var commentViewModel: CommentViewModel
    private lateinit var adapter: CommentAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)

        // 230305 tw db log
        DebugDB.getAddressLog()

        MainActivity.users.clear() // 초기화

        if(intent.hasExtra("login")) {
            val login = intent.getStringExtra("login")

            // 230228 tw 해당 user 데이터 들고 오기
            getUserInfoByLogin(login!!)
        }

        // 230305 tw comment
        db = Room.databaseBuilder(
            applicationContext,
            CommentDatabase::class.java, "comments_database"
        ).allowMainThreadQueries()
            .build()

        data = db.commentDao().getAll()
        Collections.reverse(data)
        commentView()

        binding.btnAdd.setOnClickListener {
            db.commentDao().insert(Comment(binding.editComment.text.toString()))
            Log.d(TAG, db.commentDao().getAll().toString())
            binding.editComment.setText("")
            data = db.commentDao().getAll()
            Collections.reverse(data)
            commentView()
        }
    }

    // 230227 tw all UserData
    public fun getUserInfoByLogin(name: String) {
        MainActivity.userService!!.getUserByLogin(name)
            .enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    // 실패처리
                    Log.d(TAG, "Retrofit 실패")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    var count = 0
                    // 성공처리
                    Log.d(TAG, "Retrofit 성공")
                    if(response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let{
                        //body가 있다면 그안에는 User 들어있을 것
                        Log.d(TAG,it.toString())

                        // image load
                        Glide.with(binding.Img)
                            .load(it.avaterUrl)
                            .centerCrop()
                            .into(binding.Img)

                        binding.userInfo.text = "ID : ${it.login} \n"                +
                                                "Name : ${it.name} \n"               +
                                                "Company : ${it.company} \n"         +
                                                "Location : ${it.location} \n"       +
                                                "Followers : ${it.followers} \n"     +
                                                "Followering : ${it.following} \n"   +
                                                "Created at : ${it.createdAt} \n"    +
                                                "Updated at : ${it.updatedAt} \n"
                    }
                }
            })
    }

    private fun commentView() {
        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        adapter = CommentAdapter()

        //binding.rvComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.rvComment.layoutManager = LinearLayoutManager(this)
        binding.rvComment.adapter = adapter

        commentViewModel.getComment().observe(this) { comments ->
            adapter.setComments(comments)
        }
    }

    companion object {
        const val TAG = "UserInfoActivity::"
        var data: List<Comment>? = null
    }
}