package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.recycleradapter.Adapter
import com.example.todoapp.recycleradapter.EachTask

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: Adapter

    private var rvTaskArray = ArrayList<EachTask>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//        Recycler view Initialization starts
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        Recycler view Initialization ends

//        list of Task Initialization starts
        rvTaskArray = ArrayList()
        myAdapter = Adapter(rvTaskArray)
//        list of Task Initialization ends


        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","low"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","low"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","low"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","low"))
        rvTaskArray.add(EachTask("Bath","low"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","high"))
        rvTaskArray.add(EachTask("Bath","medium"))
        rvTaskArray.add(EachTask("Bath","high"))

        recyclerView.adapter = myAdapter

    }
}