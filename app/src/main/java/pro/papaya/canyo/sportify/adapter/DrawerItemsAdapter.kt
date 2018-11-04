package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.view.DrawerItem
import pro.papaya.canyo.sportify.view.DrawerProfile

class DrawerItemsAdapter(
        private val context: Context,
        private val dataSet: ArrayList<String>) : BaseAdapter() {
  companion object {
    const val VIEW_TYPE_ITEM = 0
    const val VIEW_TYPE_PROFILE = 1
  }

  private fun getLayoutId(position: Int): Int {
    return if (getItemViewType(position) == VIEW_TYPE_ITEM) R.layout.item_drawer else R.layout.item_drawer_profile
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    return when (getLayoutId(position)) {
      R.layout.item_drawer -> {
        DrawerItem(context, dataSet[position]).itemView
      }
      else -> {
        DrawerProfile(context, "Finn Martens").drawerProfile
      }
    }
  }

  override fun getItem(position: Int): Any {
    return dataSet[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return dataSet.size
  }

  override fun getItemViewType(position: Int): Int {
    return if (position == 0) VIEW_TYPE_PROFILE else VIEW_TYPE_ITEM
  }
}