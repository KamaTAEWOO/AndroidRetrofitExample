package com.project.androidretrofitexample.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.R
import com.project.androidretrofitexample.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity() {

    public lateinit var img: ImageView
    public lateinit var userInfo: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        MainActivity.users.clear() // 초기화

        img = findViewById(R.id.Img)
        userInfo = findViewById(R.id.userInfo)

        var main = MainActivity()

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
                        Glide.with(img)
                            .load(it.avaterUrl)
                            .centerCrop()
                            .into(img)

                        userInfo.text = "ID : ${it.login} \n"                +
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