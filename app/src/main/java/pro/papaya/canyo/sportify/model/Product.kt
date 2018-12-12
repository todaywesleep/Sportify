package pro.papaya.canyo.sportify.model

import java.io.Serializable

class Product : Serializable {
  val name: String
  val squirrels: Double
  val fats: Double
  val carbohydrates: Double
  val calories: Double

  constructor(name: String, squirrels: Double, fats: Double, carbohydrates: Double, calories: Double) {
    this.name = name
    this.squirrels = squirrels
    this.fats = fats
    this.carbohydrates = carbohydrates
    this.calories = calories
  }
}