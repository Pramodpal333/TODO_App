package com.example.todoapp.recycleradapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class Adapter(private val task:List<EachTask>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    var onClick : ((EachTask) -> Unit)? = null


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
        Log.i("WHEN","${item.taskPriority}")

        when (item.taskPriority.toLowerCase()){

            "high"  -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, R.color.high))
            "low" -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, R.color.low))
            "medium" -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, R.color.medium))

        }

        holder.itemView.setOnClickListener {
            onClick?.invoke(item)
        }

    }

    override fun getItemCount(): Int {
       return task.size
    }

}


interface recyclerItem{
    fun onItemClicked(view: View)
}