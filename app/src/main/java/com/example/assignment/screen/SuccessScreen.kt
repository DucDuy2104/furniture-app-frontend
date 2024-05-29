package com.example.assignment.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.R
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.util.merriweather
import com.example.assignment.util.nunitosansFamily

@Composable
fun SuccessScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .padding(25.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SUCCESS!",
                fontFamily = merriweather,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = TextColor30
            )
            Spacer(modifier = Modifier.size(30.dp))
            Image(
                painter = painterResource(id = R.drawable.success), contentDescription = "success",
                modifier = Modifier
                    .height(270.dp)
                    .width(255.dp)
            )

            Spacer(modifier = Modifier.size(25.dp))
            Text(
                text = "Your order will be delivered soon.\n" +
                        "Thank you for choosing our app!",
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = TextColor60,
                lineHeight = 27.sp
            )
            Spacer(modifier = Modifier.size(40.dp))

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = TextColor24)
            ) {
                Text(
                    text = "Track your orders",
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(25.dp))

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(width = 2.dp, color = TextColor24, shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Track your orders",
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = TextColor30
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SuccessScreenPreview() {
    SuccessScreen()
}