package pro.papaya.canyo.sportify.model

class RequestRegisterBody {
    var id: String = ""
    var login: String = ""
    var email: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String? = null
    var birthday: String? = null
    var gender: String = ""
    var location: Location? = null

    //Init constructor
    constructor(){}

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

    fun fillBioInformation(firstName: String, lastName: String?, birthday: String?){
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
    }
}