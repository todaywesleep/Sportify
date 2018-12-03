package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.activity.CaloriesActivity
import pro.papaya.canyo.sportify.view.DrawerItem
import pro.papaya.canyo.sportify.view.DrawerProfile

class ClientItemsDrawerAdapter(context: Context, dataSet: ArrayList<String>)
  : BaseDrawerAdapter(context, dataSet) {
  private fun getOnClickListener(itemContent: String): View.OnClickListener{
    return View.OnClickListener {
      val navigationIntent: Intent = when (itemContent){
        context.getString(R.string.drawer_calories) -> {
          Intent(context, CaloriesActivity::class.java)
        }
        else -> {
          Intent(context, CaloriesActivity::class.java)
        }
      }

      context.startActivity(navigationIntent)
    }
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    return when (getLayoutId(position)) {
      R.layout.item_drawer -> {
        val item = DrawerItem(context, dataSet[position]).itemView
        item.setOnClickListener(getOnClickListener(dataSet[position]))
        item
      }
      else -> {
        DrawerProfile(context, "Finn Martens").drawerProfile
      }
    }
  }
}