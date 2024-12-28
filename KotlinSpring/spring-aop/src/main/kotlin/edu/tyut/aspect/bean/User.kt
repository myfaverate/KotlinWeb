package edu.tyut.aspect.bean

import edu.tyut.aspect.annotation.NoArg

@NoArg
data class User(
    var id: Int,
    var username: String,
    var password: String,
    var age: Int,
    var gender: String,
    var email: String,
)