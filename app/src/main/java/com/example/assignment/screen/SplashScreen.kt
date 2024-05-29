package com.example.assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.util.gelasioFamily
import com.example.assignment.route.auth_nav.AuthScreen
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.ui.theme.TextColor80

@Composable
fun SplashScreen(navController: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .paint(
            painter = painterResource(id = R.drawable.splash_bg),
            contentScale = ContentScale.FillBounds

        ),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Body(navController)
        }
    }
}

@Composable
fun Body(navController: NavHostController) {
    Column {
        Text(text = "MAKE YOUR",
            color = TextColor60,
            fontFamily = gelasioFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(text = "HOME BEAUTIFUL",
            color = TextColor30,
            fontFamily = gelasioFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.padding(top = 35.dp))
        Text(text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
            color = TextColor80,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 60.dp, end = 30.dp),
            )

        Spacer(modifier = Modifier.padding(top = 154.dp))
        TextButton(onClick = {
            navController.navigate(route = AuthScreen.LoginAuthScreen.route) {
                popUpTo(route = AuthScreen.SplashAuthScreen.route) { inclusive = true }
            }
        },
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Color.Black)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 10.dp)

        ) {
            Text(text = "Get Started",
                fontSize = 18.sp,
                fontFamily = gelasioFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
           )
        }
    }
}



