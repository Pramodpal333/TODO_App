package com.example.todoapp.recycleradapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val task:List<EachTask>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    var onClick : ((EachTask) -> Unit)? = null
//    Getting Position
    private var onItemClickListener: View.OnClickListener? = null

    fun setItemClickListener(clickListener: View.OnClickListener) {
        onItemClickListener = clickListener
    }
//    Getting Position


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(com.example.todoapp.R.id.title)
        var priority : TextView = itemView.findViewById(com.example.todoapp.R.id.priority)
        var cardBg : CardView = itemView.findViewById(com.example.todoapp.R.id.cvPriority)

         fun viewPOs(view: View) {
            view.tag = this
             var cardBg : CardView = itemView.findViewById(com.example.todoapp.R.id.cvPriority)
            cardBg.setOnClickListener(onItemClickListener)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(com.example.todoapp.R.layout.view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = task[position]
        val pos = holder.cardBg
        holder.title.text = item.taskTitle
        holder.priority.text = item.taskPriority
        Log.i("WHEN","${item.taskPriority}")


        when (item.taskPriority.toLowerCase()){

            "high"  -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, com.example.todoapp.R.color.high))
            "low" -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, com.example.todoapp.R.color.low))
            "medium" -> holder.cardBg.setCardBackgroundColor(ContextCompat.getColor(holder.cardBg.context, com.example.todoapp.R.color.medium))

        }


        holder.cardBg.setOnClickListener {
            onClick?.invoke(item)
            holder.cardBg.setTag(position)
            Log.i("POSS","POSS IS $position")

        }

    }

    override fun getItemCount(): Int {
       return task.size
    }

}


interface recyclerItem{
    fun onItemClicked(view: View)
}


