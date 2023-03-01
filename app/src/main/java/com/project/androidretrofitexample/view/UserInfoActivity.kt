package com.project.androidretrofitexample.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.R
import com.project.androidretrofitexample.databinding.ActivityUserInfoBinding
import com.project.androidretrofitexample.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)

        MainActivity.users.clear() // 초기화

        if(intent.hasExtra("login")) {
            val login = intent.getStringExtra("login")

            // 230228 tw 해당 user 데이터 들고 오기
            getUserInfoByLogin(login!!)
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

    companion object {
        const val TAG = "UserInfoActivity::"
    }
}