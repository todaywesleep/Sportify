package pro.papaya.canyo.sportify.model

import java.io.Serializable

class Location : Serializable {
    var country = ""
    var city = ""
    var street: String = ""
    var house: String = ""
    var additionalInfo: String = ""
    var longitude = "53.893009"
    var latitude = "27.567444"

    //Constructor with required fields
    constructor(country: String, city: String) {
        this.country = country
        this.city = city
    }

    //Full constructor
    constructor(country: String, city: String, street: String, house: String, additionalInfo: String,
                longitude: Double, latitude: Double)
            : this(country, city){
        this.street = street
        this.house = house
        this.additionalInfo = additionalInfo
        this.longitude = longitude.toString()
        this.latitude = latitude.toString()
    }
}