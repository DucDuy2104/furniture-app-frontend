package com.example.assignment.screen.detail_screen

import DataStoreUtil
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.assignment.R
import com.example.assignment.component.LoadingDialog
import com.example.assignment.model.Product
import com.example.assignment.screen.auth_screen.AuthViewModel
import com.example.assignment.ui.theme.ColorF0
import com.example.assignment.ui.theme.TextColor30
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.ui.theme.TextColor80
import com.example.assignment.util.buttonRadius
import com.example.assignment.util.gelasioFamily
import com.example.assignment.util.nunitosansFamily
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.math.log

@Composable
fun DetailScreen(
    navController: NavHostController, productId: Int?, detailViewModel: DetailViewModel = viewModel(
        modelClass = DetailViewModel::class.java
    )
) {

    val state = detailViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val dataStoreUtil = DataStoreUtil(context)


    if (state.value.product == null) {
        if (productId != null) {
            detailViewModel.initId(productId)
        }
    }

    fun addAmount() {
        detailViewModel.addAmount()
    }

    fun minusAmount() {
        detailViewModel.minusAmount()
    }

    if (state.value.cart != null) {
        Toast.makeText(context, "Add to cart success", Toast.LENGTH_SHORT).show()
        detailViewModel.resetCartState()
    }


    fun onAddToCart() {
        if (state.value.product != null && state.value.amount!! > 0 && dataStoreUtil.getUser() != null) {
            Log.d("Duy", "onAddToCart: ${dataStoreUtil.getUser()}")
            state.value.product?.productId?.let {
                state.value.amount?.let { it1 ->
                    dataStoreUtil.getUser()!!.userId?.let { it2 ->
                        detailViewModel.createCart(
                            productId = it, userId = it2,
                            amount = it1
                        )
                    }
                }
            }
        }
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if (state.value.product != null) {
            Column {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    DetailHeader(navController, state = state.value)
                    DetailBody(
                        state = state.value,
                        addAmount = ::addAmount,
                        minusAmount = ::minusAmount
                    )
                }
                FooterDetail(::onAddToCart)
                Spacer(modifier = Modifier.padding(10.dp))
            }
        } else {
            LoadingDialog(isLoading = state.value.isLoading)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailHeader(navController: NavHostController, state: DetailScreenState) {

    val product = state.product

    Log.d("Duy", "DetailHeader: ${product}")

    val imageList = product?.let {
        listOf(
            it.image, product.image, product.image

        )
    }

    val colorList = listOf(
        R.drawable.color_item, R.drawable.color_item1, R.drawable.color_item2
    )


    val pagerState = rememberPagerState(pageCount = {
        imageList?.size ?: 0
    })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(2000)
            coroutineScope {
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % pagerState.pageCount
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Row {
            Box(modifier = Modifier.weight(1f)) {}
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .width(323.dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp))
            ) { page ->
                if (product != null) {
                    if (imageList != null) {
                        AsyncImage(
                            model = imageList[page],
                            contentDescription = "this is detail",
                            modifier = Modifier
                                .height(455.dp)
                                .width(323.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp)

        ) {
            if (imageList != null) {
                items(imageList.size) {
                    if (pagerState.currentPage == it) {
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .width(30.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color = TextColor30)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .width(15.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color = Color(0xFFF0F0F0))
                        )
                    }
                }
            }
        }

        Row {
            Box(modifier = Modifier.weight(1f)) {}
            Box(
                modifier = Modifier
                    .width(355.dp)
                    .height(455.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .shadow(5.dp, RoundedCornerShape(40.dp))
                        .align(Alignment.CenterStart)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color.White)
                            .padding(15.dp)
                    ) {
                        items(colorList.size) {
                            Image(
                                painter = painterResource(id = colorList.get(it)),
                                contentDescription = "color",
                                modifier = Modifier
                                    .padding(bottom = if (it == colorList.size - 1) 0.dp else 20.dp)
                                    .size(34.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 70.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.White)
                        .clickable {
                            navController.popBackStack()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Sharp.KeyboardArrowLeft,
                        contentDescription = "back",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }


    }
}


@Composable
fun DetailBody(state: DetailScreenState, addAmount: () -> Unit, minusAmount: () -> Unit) {
    val product = state.product

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
    ) {
        Column {


            if (product != null) {
                product.name?.let {
                    Text(
                        text = it,
                        fontFamily = gelasioFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = TextColor30
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (product != null) {
                    Text(
                        text = "\$ ${product.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = nunitosansFamily,
                        color = TextColor30
                    )
                }
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
                                    addAmount()
                                }
                        )
                    }

                    Text(
                        text = state.amount.toString(),
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
                                    minusAmount()
                                }
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.width(150.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.start),
                    contentDescription = "star",
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Crop,
                )
                if (product != null) {
                    Text(
                        text = product.rate.toString(),
                        fontSize = 18.sp,
                        fontFamily = nunitosansFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (product != null) {
                    Text(
                        text = "(${product.vote} reviews)",
                        fontFamily = nunitosansFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor80
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            if (product != null) {
                product.description?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontFamily = nunitosansFamily,
                        fontWeight = FontWeight.Light,
                        color = TextColor60
                    )
                }
            }

        }
    }
}


@Composable
fun FooterDetail(onAddToCart: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .fillMaxWidth()
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(buttonRadius))
                    .background(color = ColorF0)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_outline),
                    contentDescription = "mark",
                    modifier = Modifier
                        .height(20.dp)
                        .width(16.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            TextButton(
                onClick = {
                    onAddToCart()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .clip(RoundedCornerShape(buttonRadius))
                    .background(color = TextColor30)
            ) {
                Text(
                    text = "Add to cart",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = nunitosansFamily,
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}
//
//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun DetailScreenPreview() {
//    DetailScreen()
//}