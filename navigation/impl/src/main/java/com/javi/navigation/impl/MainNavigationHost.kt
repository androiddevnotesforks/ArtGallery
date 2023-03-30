package com.javi.navigation.impl

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.javi.card.page.CardsPageNavGraph
import com.javi.design.system.data.models.NavigationBarItem
import com.javi.design.system.molecules.NavigationBar
import com.javi.home.HomeNavGraph
import com.javi.home.destinations.HomeScreenDestination
import com.javi.product.detail.presentation.screens.ProductDetailNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
@Composable
fun MainNavigationHost(
    activity: Activity,
    onNavController: (NavController) -> Unit
) {
    val navHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = {
                fadeIn(
                    tween(ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                fadeIn(
                    tween(ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                fadeOut(
                    tween(ANIMATION_DURATION)
                )
            }
        )
    )

    val navHostController = remember {
        mutableStateOf<NavController?>(null)
    }

    val currentDestination = navHostController.value?.currentDestinationAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                navItems = listOf(
                    NavigationBarItem(name = "Home", icon = Icons.Rounded.Star) {
                        if (currentDestination?.value != HomeScreenDestination) {
                            navHostController.value?.navigate(
                                direction = HomeScreenDestination()
                            )
                        }
                    },
                    NavigationBarItem(name = "Favorites", icon = Icons.Rounded.Favorite) {

                    },
                    NavigationBarItem(name = "Basket", icon = Icons.Rounded.ShoppingCart) {

                    }
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            DestinationsNavHost(
                navGraph = RootNavGraph,
                engine = navHostEngine,
                dependenciesContainerBuilder = {
                    dependency(calculateWindowSizeClass(activity).widthSizeClass)
                    onNavController(navController)
                    navHostController.value = navController
                }
            )
        }
    }
}
object RootNavGraph: NavGraphSpec {

    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = HomeNavGraph

    override val nestedNavGraphs = listOf(
        HomeNavGraph,
        ProductDetailNavGraph,
        CardsPageNavGraph
    )
}