package ir.romina.reza.feature_map_screen.presentation

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import ir.romina.reza.core.presentation.ui.theme.LocalSpacing
import ir.romina.reza.core.presentation.ui.theme.Shapes
import ir.romina.reza.core.presentation.util.UiEvent
import ir.romina.reza.core.presentation.util.asString
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.presentation.component.ModalBottomSheetContent
import ir.romina.reza.feature_map_screen.presentation.component.SearchTextField
import ir.romina.reza.feature_map_screen.presentation.component.StationInfoItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    onNavigateToDetailScreen: (Station) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {}
                UiEvent.NavigateUp -> {}
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(key1 = state.loading) {
        if (!state.loading) {
            val defaultPosition = LatLng(state.stations[0].lat, state.stations[0].lng)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(defaultPosition, 12f)
        }
    }

    val stations by remember(state.stations, state.searchText) {
        derivedStateOf {
            if (state.searchText.isBlank()) {
                state.stations
            } else {
                state.stations.filter { it.name.contains(state.searchText, true) }
            }
        }
    }
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        sheetContent = {
            ModalBottomSheetContent(
                onLocateClicked = { station ->
                    scope.launch {
                        bottomSheetState.bottomSheetState.collapse()
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(station.lat, station.lng), 15f)
                    }
                },
                onShowItemDetailClicked = {
                    scope.launch {
                        onNavigateToDetailScreen(it)
                        bottomSheetState.bottomSheetState.collapse()
                    }
                },
                stations = stations
            )
        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    stations.forEach { station ->
                        val radius = animateFloatAsState(
                            targetValue = station.capacity.toFloat() * 10,
                            animationSpec = tween(2000)
                        )
                        Circle(
                            center = LatLng(station.lat, station.lng),
                            fillColor = Color.Red,
                            radius = radius.value.toDouble(),
                            strokeWidth = 2f,
                            tag = station.name
                        )
                    }
                }

                SearchTextField(
                    text = state.searchText,
                    onValueChange = { viewModel.onEvent(SearchScreenEvent.OnEnteredSearchText(it)) },
                    search = {
                        keyboardController?.hide()
                    },
                    onFocusChange = {},
                    shouldShowHint = state.searchText.isBlank(),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(spacing.spaceMedium)
                )
            }
        }
    }


}