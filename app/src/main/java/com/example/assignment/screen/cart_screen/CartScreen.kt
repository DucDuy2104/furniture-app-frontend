package com.example.assignment.screen.cart_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.assignment.R
import com.example.assignment.component.LoadingDialog
import com.example.assignment.model.Cart
import com.example.assignment.model.UpdateCart
import com.example.assignment.screen.HeaderContent
import com.example.assignment.ui.theme.ColorF0
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.ui.theme.TextColor80
import com.example.assignment.ui.theme.TextColor99
import com.example.assignment.util.nunitosansFamily

@Composable
fun CartScreen(
    navController: NavHostController, cartViewModel: CartViewModel = viewModel(
        modelClass = CartViewModel::class.java
    )
) {
    val state = cartViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val carts = state.value.carts

    if(carts == null) {
        cartViewModel.getCarts(context)
    }

    fun updateCart(cartId: Int, type: String) {
        cartViewModel.updateCart(cartId, type, context)
    }


    fun createOrder() {
        cartViewModel.createOrder(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .statusBarsPadding()
    ) {
        Column {
            HeaderContent(title = "My cart", leftIcon = R.drawable.back_as, onLeftIconClick = {
                navController.popBackStack()
            })
            Spacer(modifier = Modifier.size(10.dp))
            CartBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 10.dp),
                carts ?: emptyList(),
                ::updateCart
            )
            CartFooter(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                carts ?: emptyList(),
                ::createOrder
            )
        }

        LoadingDialog(isLoading = state.value.isLoading)


    }
}

@Composable
fun CartItem(cart: Cart, updateCart: (Int, String) -> Unit) {
    Spacer(modifier = Modifier.size(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {

        Column {


            Row {
                AsyncImage(
                    model = cart.product?.image,
                    contentDescription = "item",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    cart.product?.name?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = nunitosansFamily,
                            color = TextColor60
                        )
                    }

                    Text(
                        text = "\$ ${cart.product?.price}",
                        fontSize = 16.sp,
                        fontFamily = nunitosansFamily,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(0xFFE0E0E0)),

                            ) {
                            Text(
                                text = "+",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .clickable {
                                        cart.cartId?.let { updateCart(it, "increase") }
                                    }
                            )
                        }

                        Text(
                            text = cart.amount.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = nunitosansFamily,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color(0xFFE0E0E0)),

                            ) {
                            Text(
                                text = "-",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .clickable {
                                        cart.cartId?.let { updateCart(it, "decrease") }
                                    }
                            )
                        }

                    }
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
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorF0)
            )
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun CartFooter(
    modifier: Modifier,
    carts: List<Cart>,
    createOrder: () -> Unit
) {
    val code = remember {
        mutableStateOf("")
    }

    var total = 0f
    carts.forEach{
        total += (it.product?.price?.toFloat() ?: 0f) * it.amount?.toFloat()!!
    }
    Box(modifier = modifier) {
        Column {
            BasicTextField(value = code.value, onValueChange = {
                code.value = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(3.dp)
                    .background(Color.White)
                    .padding(start = 10.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) {
                        if (code.value == "") {
                            Text(
                                text = "Enter your promo code",
                                color = TextColor99,
                                fontSize = 16.sp,
                                fontFamily = nunitosansFamily,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )

                        }
                        Box(modifier = Modifier.align(Alignment.CenterStart)) {
                            innerTextField()
                        }

                        Image(
                            painter = painterResource(id = R.drawable.send_voucher),
                            contentDescription = "send",
                            modifier = Modifier
                                .size(44.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                })
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextColor80
                )
                Text(
                    text = "$ ${String.format("%.2f", total)}",
                    fontFamily = nunitosansFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor30
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            TextButton(
                onClick = {
                          createOrder()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = TextColor24)
            ) {
                Text(
                    text = "Check out",
                    fontFamily = nunitosansFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CartBody(modifier: Modifier, carts: List<Cart>,
             updateCart: (Int , String) -> Unit) {
    LazyColumn(
        modifier = modifier
    ) {
        items(carts.size) {
            CartItem(carts[it], updateCart)
        }
    }
}

