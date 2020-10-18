package models

class Telephone {
    var fromWhere: String = ""
    var whoCallMe: String = ""

    fun callMe(myName: String) {
        println("$whoCallMe ! Call me $myName !!");
    }
}