package com.example.assignment.screen.auth_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assignment.component.LoadingDialog
import com.example.assignment.util.merriweather
import com.example.assignment.util.nunitosansFamily
import com.example.assignment.route.auth_nav.AuthScreen
import com.example.assignment.ui.theme.GrayCd
import com.example.assignment.ui.theme.TextColor90

@Composable
fun SignUpScreen (navController: NavHostController, authViewModel: AuthViewModel = viewModel(
    modelClass = AuthViewModel::class.java
)) {
    val state = authViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    fun onRegister(name: String, email: String, password: String) {
        if (state.value.isRegisterSuccess == null) {
            authViewModel.register(name, email, password)
        }
    }

    if (state.value.isRegisterSuccess == true) {
        Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
        authViewModel.resetSuccessState()
        navController.navigate(route = AuthScreen.LoginAuthScreen.route) {
            popUpTo(AuthScreen.SignUpAuthScreen.route) {
                saveState = false
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(top = 20.dp)

    ) {
        Column {
            SignUpHeader()
            Spacer(modifier = Modifier.padding(top = 20.dp))
            BodySignUpScreen(navController, ::onRegister)
        }
        LoadingDialog(isLoading = state.value.isLoading)
    }

}


@Composable
fun SignUpHeader () {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Top()
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(text = "Wellcome",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = merriweather,
            lineHeight = 45.sp
        )
    }
}





@Composable
fun BodySignUpScreen(navController: NavHostController, onRegister: (String, String, String) -> Unit) {
    val context = LocalContext.current
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }
    Row {
        Box(modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true
            )
            .weight(9f)
            .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(top = 25.dp, start = 25.dp)
            ) {
                BasicTextField(value = name.value, onValueChange = {
                    name.value = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = {innerTextField ->
                        Column {
                            Text(
                                text = "Name",
                                color = TextColor90,
                                fontFamily = nunitosansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            innerTextField()

                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Divider(color = GrayCd, thickness = 1.dp)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(top = 35.dp))
                BasicTextField(value = email.value, onValueChange = {
                    email.value = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = {innerTextField ->
                        Column {
                            Text(
                                text = "Email",
                                color = TextColor90,
                                fontFamily = nunitosansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            innerTextField()

                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Divider(color = GrayCd, thickness = 1.dp)
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(top = 35.dp))
                BasicTextField(value = password.value, onValueChange = {
                    password.value = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = {innerTextField ->
                        Column {
                            Text(
                                text = "Password",
                                color = TextColor90,
                                fontFamily = nunitosansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            innerTextField()

                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Divider(color = GrayCd, thickness = 1.dp)
                        }
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(top = 35.dp))
                BasicTextField(value = confirmPassword.value, onValueChange = {
                    confirmPassword.value = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = {innerTextField ->
                        Column {
                            Text(
                                text = "Confirm Password",
                                color = TextColor90,
                                fontFamily = nunitosansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            innerTextField()

                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Divider(color = GrayCd, thickness = 1.dp)
                        }
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.padding(top = 35.dp))
                Text(text = "Forgot Password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.padding(top = 40.dp))

                Row {
                    TextButton(onClick = {
                        var error = 0
                        if (password.value.isEmpty() || name.value.isEmpty() || email.value.isEmpty()) {
                            Toast.makeText(context, "Empty Value!", Toast.LENGTH_SHORT).show()
                            error++
                        }

                        if (error == 0 && password.value.compareTo(confirmPassword.value) != 0) {
                            Toast.makeText(context, "Password not match!", Toast.LENGTH_SHORT).show()
                            error++
                        }

                        if (error ==0 ) {
                            onRegister(name.value, email.value, password.value)
                        }


                    },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.Black)
                            .weight(12f)
                    ) {
                        Text(text = "Sign up",
                            fontFamily = nunitosansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White)
                    }
                    Box(modifier = Modifier.weight(1f)) {}
                }
                Spacer(modifier = Modifier.padding(top = 35.dp))
                Text(text = "Log in",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.popBackStack(
                                route = AuthScreen.LoginAuthScreen.route,
                                inclusive = false,
                                saveState = false
                            )
                        },
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.padding(top = 25.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}