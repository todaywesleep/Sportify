package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.CaloriesAdapter
import pro.papaya.canyo.sportify.model.Product
import pro.papaya.canyo.sportify.utils.CustomXMLReader
import java.lang.Exception

class CaloriesActivity : BaseActivity() {
  private lateinit var caloriesRecycler: RecyclerView
  private lateinit var caloriesAdapter: CaloriesAdapter
  private lateinit var caloriesViewManager: RecyclerView.LayoutManager
  private lateinit var productsStruct: CustomXMLReader

  private lateinit var squirrels: TextView
  private lateinit var fats: TextView
  private lateinit var calories: TextView
  private lateinit var carbohydrates: TextView
  private lateinit var productName: TextView

  private lateinit var measureBlock: ScrollView
  private lateinit var divider: View
  private lateinit var weightInput: EditText

  private var selectedProduct: Product? = null
  private var productWeight = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calories)
    measureBlock = findViewById(R.id.calories_measure)
    divider = findViewById(R.id.calories_divider)
    weightInput = findViewById(R.id.calories_weight_input)
    productsStruct = CustomXMLReader(this)

    productName = findViewById(R.id.calories_measure_prod_name)
    squirrels = findViewById(R.id.calories_measure_item_squirrels)
    fats = findViewById(R.id.calories_measure_item_fats)
    calories = findViewById(R.id.calories_measure_item_calories)
    carbohydrates = findViewById(R.id.calories_measure_item_carbohydrates)

    caloriesAdapter = CaloriesAdapter(this, productsStruct.getXMLData())
    caloriesViewManager = LinearLayoutManager(this)
    caloriesRecycler = findViewById<RecyclerView>(R.id.calories_items).apply {
      setHasFixedSize(true)
      layoutManager = caloriesViewManager
      adapter = caloriesAdapter
    }

    caloriesAdapter.setCallback(getCallback())
    weightInput.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        val enteredString = s.toString()
        productWeight = if (s != null && selectedProduct != null && enteredString != "") {
          try {
            s.toString().toInt()
          } catch (exception: Exception) {
            -1
          }
        } else {
          0
        }

        calculateProductStats()
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      }
    })

    calculateProductStats()
  }

  private fun calculateProductStats() {
    if (productWeight > 0 && selectedProduct != null) {
      squirrels.text = getString(R.string.calories_squirrels).plus(": ").plus(selectedProduct!!.squirrels * productWeight / 100)
      fats.text = getString(R.string.calories_fats).plus(": ").plus(selectedProduct!!.fats * productWeight / 100)
      calories.text = getString(R.string.calories_calories).plus(": ").plus(selectedProduct!!.calories * productWeight / 100)
      carbohydrates.text = getString(R.string.calories_carbohydrates).plus(": ").plus(selectedProduct!!.carbohydrates * productWeight / 100)

    } else {
      squirrels.text = getString(R.string.calories_squirrels).plus(": 0")
      fats.text = getString(R.string.calories_fats).plus(":0 ")
      calories.text = getString(R.string.calories_calories).plus(": 0")
      carbohydrates.text = getString(R.string.calories_carbohydrates).plus(": 0")
    }
  }

  private fun getCallback(): CaloriesAdapter.Callback {
    return object : CaloriesAdapter.Callback {
      override fun onItemPressed(product: Product) {
        selectedProduct = if (selectedProduct == product) {
          null
        } else {
          product
        }


        productName.text = selectedProduct?.name
        setProductInfoPanelVisibility(selectedProduct != null)
      }
    }
  }

  private fun setProductInfoPanelVisibility(isVisible: Boolean) {
    val visibilityMode = if (isVisible) {
      View.VISIBLE
    } else {
      View.GONE
    }

    divider.visibility = visibilityMode
    measureBlock.visibility = visibilityMode
  }
}