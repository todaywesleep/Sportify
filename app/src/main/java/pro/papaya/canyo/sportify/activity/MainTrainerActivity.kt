package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.TrainerEventsAdapter

class MainTrainerActivity: BaseActivity() {
  private lateinit var upcomingEventsRecycler: RecyclerView
  private lateinit var upcomingEventsAdapted: TrainerEventsAdapter
  private lateinit var upcomingEventsViewManager: RecyclerView.LayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trainer_main)

    upcomingEventsAdapted = TrainerEventsAdapter(arrayListOf("1","2","3","4","5","6","7","8","9","10"))
    upcomingEventsViewManager = LinearLayoutManager(this)
    upcomingEventsRecycler = findViewById<RecyclerView>(R.id.trainer_activity_close_events).apply {
      setHasFixedSize(true)
      layoutManager = upcomingEventsViewManager
      adapter = upcomingEventsAdapted
    }
  }
}