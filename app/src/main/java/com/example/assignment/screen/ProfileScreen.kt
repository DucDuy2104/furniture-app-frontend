package com.example.assignment.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.route.main_nav.GrapScreen
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor80
import com.example.assignment.util.nunitosansFamily


data class ProfileItem (val name: String, val content: String);

@Composable
fun ProfileScreen(paddingValues: PaddingValues, navController: NavHostController) {

    fun onGoToOrderScreen() {
        navController.navigate(route = GrapScreen.OrderScreen.route)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = paddingValues.calculateBottomPadding())) {
        Column {
            HeaderContent(title = "Profile", rightIcon = R.drawable.logout, leftIcon = R.drawable.sear_as)
            Spacer(modifier = Modifier.size(20.dp))
            ProfileInfo()
            ProfileBody(modifier = Modifier.weight(1f),
                ::onGoToOrderScreen)

        }
    }
}

@Composable
fun ProfileInfo() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.avatar), contentDescription = "avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(60.dp)))

            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Text(text = "Bruno Pham",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitosansFamily,
                    color = TextColor30)
                Text(text = "cvducduy@gmail.com",
                    fontSize = 14.sp,
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.Normal,
                    color = TextColor80)
            }
        }
    }
}

@Composable
fun ProfileBody(
    modifier: Modifier,
    onGoToOrderScreen: () -> Unit
) {
    val profileList = listOf(ProfileItem("My orders", "Already have 10 orders"),
        ProfileItem("Shipping Address", "03 Addresses"),
        ProfileItem("Payment method", "You have 2 cards"),
        ProfileItem("My reviews", "Previews for 3 items"),
        ProfileItem("Setting", "Notification, Password, FQA, Contact"))
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(profileList.size) {
                ProfileItem(profileList[it], onGoToOrderScreen)
            }
        }
    }
}

@Composable
fun ProfileItem(content: ProfileItem, onGoToOrderScreen: () -> Unit) {
    Spacer(modifier = Modifier.size(20.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, shape = RoundedCornerShape(4.dp))
        .background(color = Color.White)
        .clickable {
            onGoToOrderScreen()
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text =content.name,
                    fontFamily = nunitosansFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor24)

                Text(text = content.content,
                    fontSize = 12.sp,
                    fontFamily = nunitosansFamily,
                    color = TextColor80,
                    fontWeight = FontWeight.Normal)
            }

            Image(painter = painterResource(id = R.drawable.next), contentDescription = "next",
                modifier = Modifier
                    .width(12.dp)
                    .height(24.dp))
        }
    }
}

