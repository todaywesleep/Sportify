package pro.papaya.canyo.sportify.view

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import pro.papaya.canyo.myapplication.R

class DrawerItem(context: Context, content: String): View(context){
  val itemView: View
  val itemName: TextView

  init {
    itemView = (context as Activity).layoutInflater.inflate(R.layout.item_drawer, null)
    itemName = itemView.findViewById(R.id.drawer_item_name)
    itemName.text = content
  }
}