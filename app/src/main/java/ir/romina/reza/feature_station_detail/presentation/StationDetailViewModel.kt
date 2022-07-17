package ir.romina.reza.feature_station_detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_station_detail.domain.use_case.GetStationDataUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(
    private val useCase: GetStationDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var station by mutableStateOf(Station())
        private set

    var isLoading by mutableStateOf(true)
        private set

    private val stationId = savedStateHandle.get<String>("stationId") ?: ""

    init {
        viewModelScope.launch {
            useCase(stationId).collectLatest {
                isLoading = false
                station = it
            }
        }
    }

}