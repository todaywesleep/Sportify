package pro.papaya.canyo.sportify.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import java.util.*

class CaloriesAdapter(private val data: ArrayList<String>):
        RecyclerView.Adapter<CaloriesAdapter.MyViewHolder>(){
  class MyViewHolder(root: View) : RecyclerView.ViewHolder(root){
    var itemName: TextView = root.findViewById(R.id.calories_item_title)
  }

  override fun onCreateViewHolder(parent: ViewGroup,
                                  viewType: Int): CaloriesAdapter.MyViewHolder {
    val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calories, parent, false)
    return MyViewHolder(root)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.itemName.text = "sample"
  }

  override fun getItemCount() = data.size
}