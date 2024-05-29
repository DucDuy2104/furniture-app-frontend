package com.example.assignment.screen.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.assignment.model.Category
import com.example.assignment.model.Product
import com.example.assignment.route.main_nav.GrapScreen
import com.example.assignment.ui.theme.ColorF5
import com.example.assignment.util.gelasioFamily
import com.example.assignment.ui.theme.TextColor24
import com.example.assignment.ui.theme.TextColor60
import com.example.assignment.ui.theme.TextColor90
import com.example.assignment.ui.theme.TextColor99
import com.example.assignment.util.iconSize
import com.example.assignment.util.nunitosansFamily
import com.example.assignment.util.paddingSize

@Composable
fun HomeScreen(navController : NavHostController, paddingValues: PaddingValues, homeViewModel: HomeViewModel = viewModel(
    HomeViewModel::class.java
)) {
    val state = homeViewModel.state.collectAsStateWithLifecycle()


    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .background(color = Color.White)
        .padding(
            top = paddingSize,
            end = paddingSize,
            start = paddingSize,
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        Column {
            LoadingDialog(isLoading = state.value.isLoading)
            HomeHeader(navController)
            HomeScreenBody(navController, state = state.value)
        }
    }
}




@Composable
fun HomeHeader(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(
                id = R.drawable.search),
                contentDescription = "search",
                modifier = Modifier.size(iconSize)
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Make home",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = gelasioFamily,
                    color = TextColor90,
                    lineHeight = 25.sp
                )
                Text(
                    text = "BEAUTIFUL",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = gelasioFamily,
                    color = TextColor24,
                    lineHeight = 25.sp
                )
            }

            Image(painter = painterResource(
                id = R.drawable.cart),
                contentDescription = "cart",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.navigate(route = GrapScreen.CartScreen.route)
                    }
            )

        }
    }
}

@Composable
fun HomeScreenBody(navController: NavHostController, state: HomeScreenState) {
    val curChoose = remember {
        mutableStateOf("popular")
    }

    fun goToDetail(productId: Int) : Unit {
        navController.navigate(route = GrapScreen.DetailScreen.passProductId(productId))
    }


    fun onCategoryClick(name: String) {
        curChoose.value = name
    }
    Column {
        LazyRow {
            items(state.categories) {
                CategoryItem(category = it, isChoose = (curChoose.value == it.title), onCategoryClick = ::onCategoryClick)
            }
        }
    }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(state.products.size) {
            ProductItem(it, state.products[it], onProductClick = ::goToDetail)
        }
    }



}

@Composable
fun CategoryItem(category: Category, isChoose: Boolean = false, onCategoryClick: (name: String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 20.dp, top = paddingSize)
    ) {
        Box(modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = if (isChoose) TextColor24 else ColorF5)
            .clickable {
                category.title?.let { onCategoryClick(it) }
            }){
            AsyncImage(model = category.icon,
                contentDescription = category.title,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )

        }
        category.title?.let {
            Text(text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if(isChoose) TextColor24 else TextColor99,
                fontFamily = nunitosansFamily,
                modifier = Modifier.padding(top = 5.dp))
        }


    }
}


@Composable
fun ProductItem(index: Int, product: Product, onProductClick: (productId: Int) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp, end = if (index % 2 == 0) 10.dp else 0.dp,
                start = if (index % 2 == 0) 0.dp else 10.dp
            )
            .clickable {
                product.productId?.let { onProductClick(it) }
            }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(model = product.image, contentDescription = "image product",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(end = 10.dp, bottom = 10.dp)) {
                    Image(painter = painterResource(id = R.drawable.bag_icon), contentDescription = "bag_icon",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.BottomEnd))
                }
            }

            product.name?.let {
                Text(text = it,
                    color = TextColor60,
                    fontFamily = nunitosansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp),
                )
            }
            Text(text = "$ ${product.price}",
                color = TextColor60,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = nunitosansFamily)
        }


    }
}

