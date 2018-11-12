package pro.papaya.canyo.sportify.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class UnswipableViewPager(context: Context, attributeSet: AttributeSet) : ViewPager(context, attributeSet) {
  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    // Never allow swiping to switch between pages
    return false
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    // Never allow swiping to switch between pages
    return false
  }
}