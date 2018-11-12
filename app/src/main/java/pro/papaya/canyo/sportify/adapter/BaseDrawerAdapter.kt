package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pro.papaya.canyo.myapplication.R

abstract class BaseDrawerAdapter(
        protected val context: Context,
        protected val dataSet: ArrayList<String>) : BaseAdapter() {
  companion object {
    const val VIEW_TYPE_ITEM = 0
    const val VIEW_TYPE_PROFILE = 1
  }

  protected fun getLayoutId(position: Int): Int {
    return if (getItemViewType(position) == VIEW_TYPE_ITEM) R.layout.item_drawer else R.layout.item_drawer_profile
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    TODO("Implement in parent classes")
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

  override fun isEnabled(position: Int): Boolean {
    //Profile
    if (position == 0)
      return false
    return true
  }
}