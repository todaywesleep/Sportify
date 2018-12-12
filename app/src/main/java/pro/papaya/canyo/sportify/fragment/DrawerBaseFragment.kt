package pro.papaya.canyo.sportify.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import pro.papaya.canyo.myapplication.R

class DrawerBaseFragment : Fragment() {
  companion object {
    const val ITEM_TYPE_TAG = "item_type_tag"

    const val VIEW_TYPE_ITEM = 0
    const val VIEW_TYPE_PROFILE = 1
  }

  private var viewType: Int = VIEW_TYPE_ITEM

  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View? {

    viewType = getLayoutType()
    return inflater.inflate(getLayoutId(), container, false)
  }

  private fun getLayoutType(): Int {
    return arguments?.getInt(ITEM_TYPE_TAG) ?: VIEW_TYPE_ITEM
  }

  private fun getLayoutId(): Int{
    return if (viewType == VIEW_TYPE_ITEM) R.layout.item_drawer else R.layout.item_drawer_profile
  }
}