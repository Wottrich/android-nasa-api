package github.io.wottrich.nasaapis.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import github.io.wottrich.home.HomeScreen
import github.io.wottrich.ui.apod.AstronomyPictureOfTheDayScreen

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navConstroller = rememberNavController()
            AppNavigator(navHostController = navConstroller)
        }
    }

    @Composable
    private fun AppNavigator(navHostController: NavHostController) {
        NavHost(
            navController = navHostController,
            startDestination = "NavigationHome",
            builder = {
                navigation(startDestination = "HomeScreen", route = "NavigationHome") {
                    composable("HomeScreen") {
                        HomeScreen(
                            onItemClicked = {
                                navHostController.navigate("APODScreen")
                            }
                        )
                    }
                    composable("APODScreen") {
                        AstronomyPictureOfTheDayScreen()
                    }
                }
            }
        )
    }

}