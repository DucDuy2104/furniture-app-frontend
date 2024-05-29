package com.example.assignment.model

data class User (
    val userId: Int?,
    val name: String?,
    val image: String?,
    val email: String
)


data class LoginResponse (
    val data: UserLogin
)

data class  UserLogin(
    val userLogin: User
)

data class RegisterResponse(
    val data: UserRegister
)

data class UserRegister(
    val userRegister: Boolean
)