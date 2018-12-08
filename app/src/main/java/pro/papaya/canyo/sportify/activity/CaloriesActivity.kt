package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CaloriesAdapter
import pro.papaya.canyo.sportify.utils.CustomXMLReader

class CaloriesActivity: BaseActivity(){
  private lateinit var caloriesRecycler: RecyclerView
  private lateinit var caloriesAdapter: CaloriesAdapter
  private lateinit var caloriesViewManager: RecyclerView.LayoutManager
  private lateinit var productsStruct: CustomXMLReader

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calories)
    productsStruct = CustomXMLReader(this)

    caloriesAdapter = CaloriesAdapter(this, productsStruct.getXMLData())
    caloriesViewManager = LinearLayoutManager(this)
    caloriesRecycler = findViewById<RecyclerView>(R.id.calories_items).apply {
      setHasFixedSize(true)
      layoutManager = caloriesViewManager
      adapter = caloriesAdapter
    }
  }
}