package com.example.assignment.screen.auth_screen

import com.example.assignment.model.User

data class AuthScreenState(
    val isRegisterSuccess : Boolean? = null,
    val isLoading: Boolean = false,
    val user: User? = null
)