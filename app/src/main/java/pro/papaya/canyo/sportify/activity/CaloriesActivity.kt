package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CaloriesAdapter

class CaloriesActivity: BaseActivity(){
  private lateinit var caloriesRecycler: RecyclerView
  private lateinit var caloriesAdapter: CaloriesAdapter
  private lateinit var caloriesViewManager: RecyclerView.LayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calories)

    caloriesAdapter = CaloriesAdapter(arrayListOf("1","2","3","4","5","6","7","8","9","10"))
    caloriesViewManager = LinearLayoutManager(this)
    caloriesRecycler = findViewById<RecyclerView>(R.id.calories_items).apply {
      setHasFixedSize(true)
      layoutManager = caloriesViewManager
      adapter = caloriesAdapter
    }
  }
}