package com.example.assignment.screen.order_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assignment.model.Order
import com.example.assignment.screen.HeaderContent
import com.example.assignment.ui.theme.ColorF0
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor80
import com.example.assignment.util.nunitosansFamily
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun convertDate(dateStr: String): String {
    // Định dạng của chuỗi ngày tháng ban đầu
    val inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('zzzz')'", Locale.ENGLISH)

    // Định dạng mong muốn
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // Phân tích chuỗi ngày tháng ban đầu thành đối tượng ZonedDateTime
    val dateTime = ZonedDateTime.parse(dateStr, inputFormatter)

    // Định dạng lại đối tượng ZonedDateTime thành chuỗi theo định dạng mong muốn
    return dateTime.format(outputFormatter)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(navController: NavHostController, orderViewModel: OrderViewModel = viewModel(
    modelClass = OrderViewModel::class.java
)) {

    val state = orderViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (state.value.orders == null) {
        orderViewModel.getOrderByUserId(context)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(20.dp)) {
        Column {
            HeaderContent(title = "My order")
            OrderBody(state.value.orders ?: emptyList())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderBody(orders : List<Order>) {
    Spacer(modifier = Modifier.size(20.dp))
    LazyColumn {
        items(orders.size) {
            OrderItem(orders[it])
        }
    }
}

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderItem(order: Order) {

    var total = 0f

    order.orderItems?.forEach{
        total += it.amount?.times(it.product?.price?.toFloat()!!) ?: 0f
    }

    val amountText  = buildAnnotatedString {
        withStyle(style = SpanStyle(fontFamily = nunitosansFamily, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextColor80)) {
            append("Amount: ")
        }

        withStyle(style = SpanStyle(fontFamily = nunitosansFamily, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextColor24)) {
            append("${order.orderItems?.size}")
        }
    }

    val totalPrice  = buildAnnotatedString {
        withStyle(style = SpanStyle(fontFamily = nunitosansFamily, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextColor80)) {
            append("Total price: ")
        }

        withStyle(style = SpanStyle(fontFamily = nunitosansFamily, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextColor24)) {
            append("$ ${String.format("%.2f", total)}")
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .padding(bottom = 20.dp)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Order No${order.orderId}",
                    fontSize = 16.sp,
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = TextColor24)

                Text(text = order.createdAt?.let { convertDate(it) } ?: "",
                    fontSize = 14.sp,
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.Normal,
                    color = TextColor80)
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorF0))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = amountText)
                Text(text = totalPrice)
            }

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(36.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(color = TextColor24)) {
                    Text(text = "Detail",
                        fontSize = 16.sp,
                        fontFamily = nunitosansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                   )
                }

                Text(text = "Delivered",
                    fontSize = 16.sp,
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF27AE60),
                    modifier = Modifier.padding(end = 10.dp))
            }

        }

    }

    Spacer(modifier = Modifier.size(20.dp))
}

