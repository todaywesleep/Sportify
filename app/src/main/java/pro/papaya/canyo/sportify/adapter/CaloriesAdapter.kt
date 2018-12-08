package pro.papaya.canyo.sportify.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.model.Product
import java.util.*
import android.os.Build
import android.text.Spanned

class CaloriesAdapter(private val context: Context, private val data: ArrayList<Product>):
        RecyclerView.Adapter<CaloriesAdapter.MyViewHolder>(){
  class MyViewHolder(root: View) : RecyclerView.ViewHolder(root){
    var root: View = root.findViewById(R.id.calories_item_root)
    var itemName: TextView = root.findViewById(R.id.calories_item_title)
    var itemSquirrels: TextView = root.findViewById(R.id.calories_item_squirrels)
    var itemFats: TextView = root.findViewById(R.id.calories_item_fats)
    var itemCarbohydrates: TextView = root.findViewById(R.id.calories_item_calories)
    var itemCalories: TextView = root.findViewById(R.id.calories_item_carbohydrates)
  }
  private var selectedItem: Int = -1

  private fun getItemOnClickListener(position: Int): View.OnClickListener {
    return View.OnClickListener {
      if (position != selectedItem){
        selectedItem = position
        notifyDataSetChanged()
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup,
                                  viewType: Int): CaloriesAdapter.MyViewHolder {
    val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calories, parent, false)
    return MyViewHolder(root)
  }

  private fun setSpannableText(original: String, additional: String, text: TextView){
    val sourceString = "<b>$original: </b> $additional"
    text.text = fromHtml(sourceString)
  }

  private fun fromHtml(html: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
      Html.fromHtml(html)
    }
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.itemName.text = data[position].name
    setSpannableText(context.getString(R.string.calories_squirrels), data[position].squirrels.toString(), holder.itemSquirrels)
    setSpannableText(context.getString(R.string.calories_calories), data[position].calories.toString(), holder.itemCalories)
    setSpannableText(context.getString(R.string.calories_carbohydrates), data[position].carbohydrates.toString(), holder.itemCarbohydrates)
    setSpannableText(context.getString(R.string.calories_fats), data[position].fats.toString(), holder.itemFats)

    var newItemBackground = R.drawable.item_calories
    if (holder.adapterPosition == selectedItem){
      newItemBackground = R.drawable.item_calories_pressed
      selectedItem = holder.adapterPosition
    }

    holder.root.setBackgroundResource(newItemBackground)
    holder.root.setOnClickListener(getItemOnClickListener(holder.adapterPosition))
  }

  override fun getItemCount() = data.size
}