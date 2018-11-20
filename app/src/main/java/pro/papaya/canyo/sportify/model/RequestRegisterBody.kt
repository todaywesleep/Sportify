package pro.papaya.canyo.sportify.model

class RequestRegisterBody {
    var id: String = ""
    var login: String = ""
    var email: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var birthday: String = ""
    var gender: String = ""
    var location: Location? = null

    //Constructor with required fields
    constructor(id: String, login: String, email: String, password: String, firstName: String) {
        this.id = id
        this.login = login
        this.email = email
        this.password = password
        this.firstName = firstName
    }

    //Full-params constructor
    constructor(id: String, login: String, email: String, password: String, firstName: String,
                lastName: String, birthday: String, gender: String, location: Location)
            : this(id, login, email, password, firstName) {
        this.lastName = lastName
        this.birthday = birthday
        this.gender = gender
        this.location = location
    }
}