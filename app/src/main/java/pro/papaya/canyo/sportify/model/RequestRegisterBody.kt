package pro.papaya.canyo.sportify.model

class RequestRegisterBody{
    var id: String = ""
    var login: String = ""
    var email: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var birthday: String = ""
    var gender: String = ""
    var location: Location? = null
}