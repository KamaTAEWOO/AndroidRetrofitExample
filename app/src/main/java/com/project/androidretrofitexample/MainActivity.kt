package com.project.androidretrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.androidretrofitexample.adapter.RecyclerViewAdapter
import com.project.androidretrofitexample.model.User
import com.project.androidretrofitexample.model.Users
import com.project.androidretrofitexample.sevice.UserService
import com.project.androidretrofitexample.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load data
        loadData()
        getUsersInfo()
    }

    // 230227 tw Retrofit 사용
    public fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(MAINURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UserService::class.java)
    }

    // 230227 tw all UserData
    private fun getUsersInfo() {
        userService!!.getUsers()
            .enqueue(object: Callback<List<Users>> {
                override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                    // 실패처리
                    Log.d(TAG, "Retrofit 실패")
                }

                override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                    var count = 0
                    // 성공처리
                    Log.d(TAG, "Retrofit 성공")
                    if(response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let{
                        //body가 있다면 그안에는 User 들어있을 것
                        //Log.d(TAG,it.toString())

                        it.forEach { user ->
                            Log.d(TAG, user.toString())
                            users.add(user);
                            count ++
                            if(count == it.size)
                                userView() // RecyclearView
                        }
                    }
                }
            })
    }

    // 230227 tw RecuclerView 사용하여 UserView
    private fun userView() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        adapter = RecyclerViewAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.rvUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        userViewModel.getUsers().observe(this, {users ->
            adapter.setUsers(users)
        })
    }

    companion object {
        private const val TAG = "MainActivity::"
        public val users = arrayListOf<Users>()
        const val MAINURL = "https://api.github.com/"
        var userService: UserService? = null
    }
}