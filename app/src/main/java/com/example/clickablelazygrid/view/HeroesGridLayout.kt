package com.example.clickablelazygrid.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clickablelazygrid.R
import com.example.clickablelazygrid.Screen
import com.example.clickablelazygrid.model.HeroesData
import com.example.clickablelazygrid.ui.theme.Purple500
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ExperimentalFoundationApi
@Composable
fun HeroesGridLayout(navController: NavController) {
    val context = LocalContext.current
    val dataFileString = getJsonDataFromAsset(context, "HeroesList.json")
    val gson = Gson()
    val gridSampleType = object :TypeToken<List<HeroesData>>(){}.type
    val heroData : List<HeroesData> = gson.fromJson(dataFileString,gridSampleType)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Purple500)
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Heroes",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ) {
            items(heroData) { data ->
                HeroDataGridItem(data, navController)
            }
        }
    }
}

@Composable
fun HeroDataGridItem(data: HeroesData, navController: NavController) {
    Card(modifier = Modifier
        .clickable {
            val itemId = Gson().toJson(data)
            navController.navigate(Screen.HeroDetails.route + "/$itemId")
        }
        .padding(10.dp)
        .fillMaxSize(),
        elevation =  5.dp,
        shape = RoundedCornerShape(5.dp))
    {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(
                id = when(data.id) {
                    1L -> R.drawable.abaddon_profile
                    2L -> R.drawable.alchemist_profile
                    3L -> R.drawable.ancient_apparition
                    4L -> R.drawable.antimage
                    else -> R.drawable.abaddon
                }),
                contentDescription = "Images",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp))
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                text = data.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize =  12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(1.dp))

            Text(
                text = "View Detail",
                modifier = Modifier
                    .padding(7.dp,0.dp,0.dp,20.dp),
                fontSize =  12.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

fun getJsonDataFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }

}
