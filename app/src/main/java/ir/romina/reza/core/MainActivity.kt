package ir.romina.reza.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import ir.romina.reza.core.presentation.ui.theme.BikeFinderTheme
import ir.romina.reza.core.presentation.util.Route
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.presentation.SearchScreen
import ir.romina.reza.feature_station_detail.StationDetailScreen
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
                                    Timber.e("$it")
                                    navController.navigate(
                                        route = Route.StationDetailScreen + "/${it.name}/${it.capacity}/${it.lat}/${it.lng}/${it.rental_method}"
                                    )
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(
                            route = Route.StationDetailScreen + "/{name}/{capacity}/{lat}/{lng}/{rentalMethod}",
                            arguments = listOf(
                                navArgument("lat") {
                                    type = NavType.StringType
                                },
                                navArgument("lng") {
                                    type = NavType.StringType
                                },
                                navArgument("name") {
                                    type = NavType.StringType
                                },
                                navArgument("capacity") {
                                    type = NavType.StringType
                                },
                                navArgument("rentalMethod") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            Timber.e("${it.arguments}")
                            val station = Station(
                                name = it.arguments?.getString("name")!!,
                                lat = it.arguments?.getString("lat")!!.toDouble(),
                                lng = it.arguments?.getString("lng")!!.toDouble(),
                                capacity = it.arguments?.getString("capacity")!!,
                                rental_method = it.arguments?.getString("rentalMethod")!!,
                                station_id = ""
                            )
                            StationDetailScreen(station = station)
                        }
                    }
                }
            }
        }
    }
}