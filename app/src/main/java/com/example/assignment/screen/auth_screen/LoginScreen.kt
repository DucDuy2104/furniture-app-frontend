package com.example.assignment.screen.auth_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.component.LoadingDialog
import com.example.assignment.util.merriweather
import com.example.assignment.util.nunitosansFamily
import com.example.assignment.route.auth_nav.AuthScreen
import com.example.assignment.route.constants.AUTHENTICATION_ROUTE
import com.example.assignment.route.main_nav.GrapScreen
import com.example.assignment.ui.theme.GrayCd
import com.example.assignment.ui.theme.TextColor90

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel = viewModel(
    modelClass = AuthViewModel::class.java
)) {
    val state = authViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    fun onLogin(email: String, password: String) {
        if (state.value.user == null) {
            authViewModel.login(email, password, context)
        }
    }

    if (state.value.user != null){
        Log.d("Duy", "LoginScreen: ${state.value.user}")
        Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
        navController.navigate(route = GrapScreen.MainBottomBarScreen.route) {
            popUpTo(route = AUTHENTICATION_ROUTE){
                inclusive = true
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
            Header()
            Spacer(modifier = Modifier.padding(top = 20.dp))
            BodyLoginScreen(navController, ::onLogin)
        }

        LoadingDialog(isLoading = state.value.isLoading)
    }

}




@Composable
fun Header() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Top()
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(
            text = "Hello!",
            fontFamily = merriweather,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            color = TextColor90,
            lineHeight = 45.sp
        )
        Text(
            text = "Wellcome Back",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = merriweather,
            lineHeight = 45.sp
        )
    }
}


@Composable
fun Top() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .background(Color.Black)
                .weight(1f)
                .height(1.dp)
        ) {}
        Spacer(modifier = Modifier.padding(end = 10.dp))
        Image(
            painter = painterResource(id = R.drawable.header_image),
            contentDescription = "Top image",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Surface(
            modifier = Modifier
                .background(Color.Black)
                .weight(1f)
                .height(1.dp)
        ) {}
    }
}


@Composable
fun BodyLoginScreen(navController: NavHostController, onLogin: (String, String) -> Unit) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Row {
        Box(
            modifier = Modifier
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
                BasicTextField(value = email.value, onValueChange = {
                    email.value = it
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = { innerTextField ->
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
                BasicTextField(
                    value = password.value, onValueChange = {
                        password.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    decorationBox = { innerTextField ->
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
                Text(
                    text = "Forgot Password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(top = 40.dp))

                Row {
                    TextButton(
                        onClick = {
                            onLogin(email.value, password.value)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.Black)
                            .weight(12f)
                    ) {
                        Text(
                            text = "Log in",
                            fontFamily = nunitosansFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {}
                }
                Spacer(modifier = Modifier.padding(top = 35.dp))
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(route = AuthScreen.SignUpAuthScreen.route)
                        },
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(top = 25.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}