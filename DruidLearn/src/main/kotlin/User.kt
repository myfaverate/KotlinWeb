package edu.tyut

data class User(
    @ColumnName(value = "id")
    private var sid: Int = 0,
    private var account: String = "",
    private var nickname: String = "",
    private var password: String = "",
)