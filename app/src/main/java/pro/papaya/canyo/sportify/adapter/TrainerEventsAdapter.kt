package pro.papaya.canyo.sportify.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.utils.Utils.Companion.getRequestDateFormat
import java.util.*

class TrainerEventsAdapter(private val data: ArrayList<String>) :
        RecyclerView.Adapter<TrainerEventsAdapter.MyViewHolder>() {
  class MyViewHolder(root: View) : RecyclerView.ViewHolder(root){
    var eventName: TextView = root.findViewById(R.id.event_item_name)
    var eventContent: TextView = root.findViewById(R.id.event_item_content)
    var eventDate: TextView = root.findViewById(R.id.event_item_date)
  }

  override fun onCreateViewHolder(parent: ViewGroup,
                                  viewType: Int): TrainerEventsAdapter.MyViewHolder {
    val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.trainer_event_item, parent, false)
    return MyViewHolder(root)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.eventName.text = holder.eventName.text.toString().plus(" ").plus(data[position])
    holder.eventContent.text = holder.eventContent.text.toString().plus(" ").plus(data[position])
    holder.eventDate.text = getRequestDateFormat(Date())
  }

  override fun getItemCount() = data.size
}