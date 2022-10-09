package com.example.clickablelazygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clickablelazygrid.model.HeroesData
import com.example.clickablelazygrid.ui.theme.ClickableLazyGridTheme
import com.example.clickablelazygrid.view.HeroDetails
import com.example.clickablelazygrid.view.HeroesGridLayout
import com.google.gson.Gson

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickableLazyGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HeroesGridLayout.route) {
        composable(route = Screen.HeroesGridLayout.route) {
            HeroesGridLayout(navController = navController)
        }
        composable(route = Screen.HeroDetails.route + "/{item}",
            arguments = listOf(
                navArgument("item"){
                    type = NavType.StringType
                    nullable = true})
        ){
                navBackStackEntry ->
            navBackStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, HeroesData::class.java)
                HeroDetails(data = item)
            }
        }


    }
}
