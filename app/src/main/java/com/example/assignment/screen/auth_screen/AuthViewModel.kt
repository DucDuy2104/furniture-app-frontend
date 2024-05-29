package com.example.assignment.screen.auth_screen

import DataStoreUtil
import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.retrofit.auth.AuthRepository
import com.example.assignment.retrofit.category.GrapQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val authRepository = AuthRepository()

    private val _state = MutableStateFlow(AuthScreenState())
    val state =_state.asStateFlow()



    fun register(name: String, email: String, password: String) {
        val query = """
            mutation {
              userRegister(registerInput: {name: "$name", email: "$email", password: "$password"})
            }
        """.trimIndent()


        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            authRepository.register(GrapQuery(query)) {isSuccess ->
                _state.update {
                    it.copy(isRegisterSuccess = isSuccess)
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun resetSuccessState() {
        viewModelScope.launch {
            _state.update {
                it.copy(isRegisterSuccess = null, user = null, isLoading = false)
            }
        }
    }


    fun login(email: String, password: String, context: Context) {
        var dataStoreUtil = DataStoreUtil(context)

        val query = """
            mutation{
              userLogin(loginInput:{ email: "$email", password: "$password"}){
                userId
                name
                email
                image
              }
            }
        """.trimIndent()
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            authRepository.login(GrapQuery(query)) {user->
                Log.d("Duy", "login: $user")
                dataStoreUtil.saveUser(user = user)
                _state.update {
                    it.copy(user = user)
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

}