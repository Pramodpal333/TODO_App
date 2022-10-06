package com.example.todoapp.recycleradapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class Adapter(private val task:List<EachTask>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.title)
        var priority : TextView = itemView.findViewById(R.id.priority)
        var cardBg : CardView = itemView.findViewById(R.id.cvPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        val item = task[position]
        holder.title.text = item.taskTitle
        holder.priority.text = item.taskPriority

        when (item.taskPriority.toLowerCase()){

            "high"  -> holder.cardBg.setCardBackgroundColor(Color.parseColor("#e71d36"))
            "low" -> holder.cardBg.setCardBackgroundColor(Color.parseColor("#ffca3a"))
            "medium" -> holder.cardBg.setCardBackgroundColor(Color.parseColor("#8ac926"))

        }
    }

    override fun getItemCount(): Int {
       return task.size
    }

}