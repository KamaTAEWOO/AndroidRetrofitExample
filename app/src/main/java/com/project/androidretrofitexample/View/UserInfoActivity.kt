package com.project.androidretrofitexample.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.project.androidretrofitexample.MainActivity
import com.project.androidretrofitexample.Model.User
import com.project.androidretrofitexample.R

class UserInfoActivity : AppCompatActivity() {

    public lateinit var img: ImageView
    public lateinit var userInfo: TextView

    // UserData
    private val usersData = MainActivity.users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        img = findViewById(R.id.Img)
        userInfo = findViewById(R.id.userInfo)

        if(intent.hasExtra("login")) {
            val login = intent.getStringExtra("login")

            for(i in 0..usersData.size - 1) {
                if(login.equals(usersData[i].login)) {
                    userInfo.text = usersData[i].toString().replace("User(", "")
                        .replace(")","").replace(",", "\n")
                }
            }
        }
    }
}