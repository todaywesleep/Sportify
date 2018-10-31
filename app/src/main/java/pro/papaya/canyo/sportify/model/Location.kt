package pro.papaya.canyo.sportify.model

import java.io.Serializable

class Location: Serializable{
    constructor(country: String, city: String, longitude: Double, latitude: Double){
        this.country = country
        this.city = city
        this.longitude = longitude
        this.latitude = latitude
    }

    var country = ""
    var city = ""
    var longitude = 53.893009
    var latitude = 27.567444
}