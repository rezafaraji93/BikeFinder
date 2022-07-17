package ir.romina.reza.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ir.romina.reza.core.presentation.ui.theme.BikeFinderTheme
import ir.romina.reza.core.presentation.util.Route
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.presentation.SearchScreen
import ir.romina.reza.feature_station_detail.presentation.StationDetailScreen
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BikeFinderTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    NavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController,
                        startDestination = Route.MainScreen
                    ) {
                        composable(
                            route = Route.MainScreen
                        ) {
                            SearchScreen(
                                onNavigateToDetailScreen = {
                                    navController.navigate(
                                        route = Route.StationDetailScreen + "/${it}"
                                    )
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(
                            route = Route.StationDetailScreen + "/{stationId}",
                            arguments = listOf(
                                navArgument("stationId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            StationDetailScreen()
                        }
                    }
                }
            }
        }
    }
}