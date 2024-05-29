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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.route.main_nav.GrapScreen
import com.example.assignment.ui.theme.ColorF0
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.util.merriweather
import com.example.assignment.util.nunitosansFamily

@Composable
fun FavoriteScreen(paddingValues: PaddingValues, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = paddingValues.calculateBottomPadding() + 10.dp)
    ) {
        Column {
            HeaderContent(
                leftIcon = R.drawable.sear_as,
                title = "Favorites",
                rightIcon = R.drawable.cart_as,
                onRightIconClick = {
                    navController.navigate(route = GrapScreen.CartScreen.route)
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            FavoriteBody()
        }
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(TextColor24)
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)) {
            Text(text = "Add all to my cart",
                fontFamily = nunitosansFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }
    }
}

@Composable
fun FavoriteBody() {
    LazyColumn(
        modifier = Modifier.padding(bottom = 60.dp)
    ) {
        items(10) {
            FavoriteItem()
        }
    }
}

@Composable
fun FavoriteItem() {
    Spacer(modifier = Modifier.size(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {

        Column {


            Row {
                Image(
                    painter = painterResource(id = R.drawable.item_image),
                    contentDescription = "item",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Coffee Chair",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = nunitosansFamily,
                        color = TextColor60
                    )

                    Text(
                        text = "\$ 20.00",
                        fontSize = 16.sp,
                        fontFamily = nunitosansFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "delete",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.size(42.dp))

                    Image(
                        painter = painterResource(id = R.drawable.black_bag),
                        contentDescription = "bag",
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorF0))
        }
    }
}


@Composable
fun HeaderContent(
    leftIcon: Int = 0,
    title: String,
    rightIcon: Int = 0,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onLeftIconClick: (() -> Unit)? = null,
    onRightIconClick: (()-> Unit)? = null

) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (leftIcon != 0) {
                Image(
                    painter = painterResource(id = leftIcon), contentDescription = "back",
                    modifier = Modifier.size(20.dp)
                        .clickable {
                            if (onLeftIconClick != null) {
                                onLeftIconClick()
                            }
                        }
                )
            } else {
                Box(modifier = Modifier.size(20.dp))
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor30,
                fontFamily = merriweather
            )

            if (rightIcon != 0) {
                Image(
                    painter = painterResource(id = R.drawable.cart_as), contentDescription = "back",
                    modifier = Modifier.size(20.dp)
                        .clickable {
                            if (onRightIconClick != null) {
                                onRightIconClick()
                            }
                        }
                )
            } else {
                Box(modifier = Modifier.size(20.dp))
            }
        }


    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun FavoriteScreenPreview() {
//    FavoriteScreen()
//}