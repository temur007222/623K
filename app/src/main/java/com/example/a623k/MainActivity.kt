package com.example.a623k

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

 private var sharedPreferences: SharedPreferences? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("userInfo", 0)

        val bt = findViewById<Button>(R.id.bt)
        val et = findViewById<EditText>(R.id.et)
        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)

        bt.setOnClickListener {

            val name1 = name.text.toString()
            val age1 = age.text.toString()

            val myObject = MyObject(age1, name1)

            val prefsEditor = sharedPreferences?.edit()
            val gson = Gson()
            val json = gson.toJson(myObject)
            prefsEditor?.putString("MyObject", json)
            prefsEditor?.apply()

            val gson1 = Gson()
            val json1 = sharedPreferences?.getString("MyObject", "")
            val obj = gson1.fromJson(json1, MyObject::class.java)
            et.setText("Age: " + obj.name + "\nName: " + obj.age)
        }
    }
}