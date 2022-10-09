package com.example.clickablelazygrid.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clickablelazygrid.R
import com.example.clickablelazygrid.model.HeroesData
import com.example.clickablelazygrid.ui.theme.Purple500

@Composable
fun HeroDetails(data: HeroesData) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text ="Heroes",
                color = Color.White,
                fontSize =  20.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.padding(1.dp))

        Image(painter = painterResource(
            id =  when(data.id){
                1L -> R.drawable.abaddon_profile
                2L -> R.drawable.alchemist_profile
                3L -> R.drawable.ancient_apparition
                4L -> R.drawable.antimage
                else -> R.drawable.abaddon
            }
        ),
            contentDescription = "Image",
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = data.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Black,
            fontSize =  20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(1.dp))

        Text(
            text = data.description,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp),
            color = Color.Black,
            fontSize =  16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
@Preview
fun HeroDataDetailsPrev() {
    HeroDetails(HeroesData(1, "Abaddon", "View Detail"))
}