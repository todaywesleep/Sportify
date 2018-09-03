package pro.papaya.canyo.sportify.model

import java.io.Serializable

class RegisterBody: Serializable {
    constructor(login: String, password: String, first_name: String, second_name: String, birthday: String, sex: String, location: Location?){
        this.login = login
        this.password = password
        this.first_name = first_name
        this.first_name = second_name
        this.birthday = birthday
        this.sex = sex
        this.location = location
    }

    var login: String = ""
    var password: String = ""
    var first_name: String = ""
    var second_name: String = ""
    var birthday: String = ""
    var sex: String = ""
    var location: Location? = null
}