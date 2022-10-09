package com.example.todoapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.recycleradapter.Adapter
import com.example.todoapp.recycleradapter.EachTask

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: Adapter
    private lateinit var btnAdd: Button

    private var rvTaskArray = ArrayList<EachTask>()
    private lateinit var dailogBinding: View
    private lateinit var myDial: Dialog
    private lateinit var updateDailogBinding: View
    private lateinit var updateDial: Dialog

    private lateinit var ivDeleteAll : ImageView
    private lateinit var btnSave : Button
    private lateinit var createdTitle : EditText
    private lateinit var createdPriority : EditText
    private lateinit var tvAlertPriority : TextView
    private lateinit var ivEmpty : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        dailogBinding = layoutInflater.inflate(R.layout.create_card, null)
        myDial = Dialog(this)

        updateDailogBinding = layoutInflater.inflate(R.layout.update_card, null)
        updateDial = Dialog(this)

        btnSave = dailogBinding.findViewById(R.id.btnSave)
        createdTitle = dailogBinding.findViewById(R.id.create_title)
        createdPriority = dailogBinding.findViewById(R.id.create_priority)
        tvAlertPriority = dailogBinding.findViewById(R.id.tvAlertPriority)

        ivEmpty = findViewById(R.id.ivEmpty)
        myDial.setContentView(dailogBinding)
        updateDial.setContentView(updateDailogBinding)
//        Recycler view Initialization starts
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        Recycler view Initialization ends

//        list of Task Initialization starts
        ivDeleteAll = findViewById(R.id.ivDeleteAll)
        btnAdd = findViewById(R.id.add)
        rvTaskArray = ArrayList()
        myAdapter = Adapter(rvTaskArray)
//        list of Task Initialization ends


//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "low"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "low"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "low"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "low"))
//        rvTaskArray.add(EachTask("Bath", "low"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "high"))
//        rvTaskArray.add(EachTask("Bath", "medium"))
//        rvTaskArray.add(EachTask("Bath", "high"))
emptyImageShow(rvTaskArray)
        recyclerView.adapter = myAdapter

        ivDeleteAll.setOnClickListener{
            rvTaskArray.clear()
            recyclerView.adapter = myAdapter
           emptyImageShow(rvTaskArray)
        }

        btnAdd.setOnClickListener {

//            Show Dialog
            myDial.setCancelable(true)
            myDial.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDial.show()

//              Save Task
                btnSave.setOnClickListener {
                      val finalTitle = createdTitle.getText().toString()
                    val finalprio = createdPriority.getText().toString().toLowerCase()

                    Log.i("TAG","PRIO is $finalprio")

                    if (finalTitle.isNullOrEmpty()){

                        Toast.makeText(this,"Please Enter Title",Toast.LENGTH_SHORT).show()

                    }  else if (finalprio.isNullOrEmpty()){

                        Toast.makeText(this,"Please Set Priority of your task",Toast.LENGTH_SHORT).show()

                    }  else if (finalprio.isNotBlank() && finalTitle.isNotBlank()){

                      if ((finalprio == "high" || finalprio == "low"||  finalprio == "medium")) {
                            rvTaskArray.add(EachTask(finalTitle,finalprio))
                            emptyImageShow(rvTaskArray)
                            recyclerView.adapter = myAdapter
                            myDial.dismiss()

                            createdTitle.setText("")
                            createdPriority.setText("")
                            tvAlertPriority.visibility = View.GONE

                        } else {
                         tvAlertPriority.visibility = View.VISIBLE
                        }

                    }

                }

            //              Save Task

            myAdapter.onClick = {
                updateDial.setCancelable(true)
                updateDial.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                updateDial.show()
            }


        }

    }

    private fun emptyImageShow(rvTaskArray: ArrayList<EachTask>) {

        if (rvTaskArray.isNotEmpty()){
            ivEmpty.visibility = View.GONE
        } else { ivEmpty.visibility = View.VISIBLE}
    }


    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}


