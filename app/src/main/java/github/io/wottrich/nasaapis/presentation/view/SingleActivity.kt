package github.io.wottrich.nasaapis.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import github.io.wottrich.domain.AvailableItemType.AstronomyPictureOfTheDay
import github.io.wottrich.domain.AvailableItemType.EarthPolychromaticImagingCamera
import github.io.wottrich.home.HomeScreen
import github.io.wottrich.ui.apod.AstronomyPictureOfTheDayScreen
import github.io.wottrich.ui.epic.EarthPolychromaticImagingCameraScreen

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@OptIn(ExperimentalAnimationApi::class)
class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            AppNavigator(navHostController = navController)
        }
    }

    @Composable
    private fun AppNavigator(navHostController: NavHostController) {
        AnimatedNavHost(
            navController = navHostController,
            startDestination = "NavigationHome",
            builder = {
                navigation(startDestination = "HomeScreen", route = "NavigationHome") {
                    composable(
                        route = "HomeScreen",
                    ) {
                        HomeScreen(
                            onItemClicked = {
                                val action = when (it) {
                                    AstronomyPictureOfTheDay -> "APODScreen"
                                    EarthPolychromaticImagingCamera -> "EPICScreen"
                                }
                                navHostController.navigate(action)
                            }
                        )
                    }
                    defaultComposableAnimation(route = "APODScreen") {
                        AstronomyPictureOfTheDayScreen(
                            onBackButton = {
                                navHostController.popBackStack()
                            }
                        )
                    }
                    defaultComposableAnimation("EPICScreen") {
                        EarthPolychromaticImagingCameraScreen(
                            onBackButton = {
                                navHostController.popBackStack()
                            }
                        )
                    }
                }
            }
        )
    }

    private fun NavGraphBuilder.defaultComposableAnimation(
        route: String,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        composable(
            route = route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
            }
        ) {
            content(it)
        }
    }
}