package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainScreen : AppCompatActivity(){

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        recyclerView=findViewById(R.id.recyclerView)

        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://spotify23.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyInterface::class.java)

        val retrofitData = retrofitBuilder.getSearchData("4WNcduiCmDNfmTEz7JvmLv")


        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api call is successful and then show in your app

                 val responseBody=response.body()
                val tracklist=responseBody?.tracks!!
                myAdapter=MyAdapter(this@MainScreen,tracklist)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager=LinearLayoutManager(this@MainScreen)
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if api fail then show this
                Log.d("MainScreen","onFailure"+ t.message)
            }
        })




    }
}
