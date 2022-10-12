package com.example.todoapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var ivDeleteAll: ImageView
    private lateinit var btnSave: Button
    private lateinit var createdTitle: EditText
    private lateinit var createdPriority: EditText
    private lateinit var tvAlertPriority: TextView
    private lateinit var ivEmpty: ImageView


//    Update Card Elements
    private lateinit var etTitleUpdate : EditText
    private lateinit var etPriorityUpdate : EditText
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

// Create Dialog
        dailogBinding = layoutInflater.inflate(R.layout.create_card, null)
        myDial = Dialog(this)
//Update Dialog
        updateDailogBinding = layoutInflater.inflate(R.layout.update_card, null)
        updateDial = Dialog(this)
//        Update Intial
        etTitleUpdate = updateDailogBinding.findViewById(R.id.etTitleUpdate)
        etPriorityUpdate = updateDailogBinding.findViewById(R.id.etPriorityUpdate)
        btnUpdate = updateDailogBinding.findViewById(R.id.btnUpdate)
        btnDelete = updateDailogBinding.findViewById(R.id.btnDelete)
//        Update Intial

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


       // rvTaskArray.add(EachTask("Bath", "High"))


        emptyImageShow(rvTaskArray)
        refreshRecycler()

        ivDeleteAll.setOnClickListener {
            rvTaskArray.clear()
            recyclerView.adapter = myAdapter
            emptyImageShow(rvTaskArray)
        }

        btnAdd.setOnClickListener {

//            Show Dialog
            displayPopUp(myDial)

//              Save Task
            btnSave.setOnClickListener {
                val finalTitle = createdTitle.getText().toString()
                val finalprio = createdPriority.getText().toString().toLowerCase()

                Log.i("TAG", "PRIO is $finalprio")

                if (finalTitle.isNullOrEmpty()) {

                    Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show()

                } else if (finalprio.isNullOrEmpty()) {

                    Toast.makeText(this, "Please Set Priority of your task", Toast.LENGTH_SHORT)
                        .show()

                } else if (finalprio.isNotBlank() && finalTitle.isNotBlank()) {

                    if ((finalprio == "high" || finalprio == "low" || finalprio == "medium")) {
                        rvTaskArray.add(EachTask(finalTitle, finalprio))
                        emptyImageShow(rvTaskArray)
                        refreshRecycler()
                        myDial.dismiss()

                        createdTitle.setText("")
                        createdPriority.setText("")
                        tvAlertPriority.visibility = View.GONE

                    } else {
                        tvAlertPriority.visibility = View.VISIBLE
                    }

                }

            }

//              Save Task Ends


            myAdapter.onClick = {
                etTitleUpdate.setText(it.taskTitle)
                etPriorityUpdate.setText(it.taskPriority)
                displayPopUp(updateDial)

                val index = rvTaskArray.indexOf(it)
                Log.i("yooooooooooooooo","what is this ${index}")



              btnUpdate.setOnClickListener {
                  val updateTitle = etTitleUpdate.text.toString()
                  val updatePriority = etPriorityUpdate.text.toString()



                  if ((updatePriority == "high" || updatePriority == "low" || updatePriority == "medium")) {
                      rvTaskArray[it.id].taskTitle = updateTitle

                      Log.i("yooooooooooooooo","what is this ${it.id}")
                      emptyImageShow(rvTaskArray)
                      refreshRecycler()
                      updateDial.dismiss()
              }



                }



                btnDelete.setOnClickListener {
                    if (rvTaskArray.size == 0){

                    } else {
                        rvTaskArray.removeAt(index)
                    }
                }


                fun updateTask(task : String , prio: String){
                    it.taskTitle = task
                    it.taskPriority = prio
                }

            }


        }

    }

    private fun displayPopUp(dialog: Dialog) {

        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    //    Recycler View Refresher
    private fun refreshRecycler() {
        recyclerView.adapter = myAdapter
    }

    //    Empty Task Image Visibility
    private fun emptyImageShow(rvTaskArray: ArrayList<EachTask>) {

        if (rvTaskArray.isNotEmpty()) {
            ivEmpty.visibility = View.GONE
        } else {
            ivEmpty.visibility = View.VISIBLE
        }
    }

    // Double Back Exit Function
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


