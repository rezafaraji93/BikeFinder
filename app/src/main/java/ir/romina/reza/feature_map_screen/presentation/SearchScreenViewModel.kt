package ir.romina.reza.feature_map_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.reza.R
import ir.romina.reza.core.presentation.util.Route
import ir.romina.reza.core.presentation.util.UiEvent
import ir.romina.reza.core.presentation.util.UiText
import ir.romina.reza.core.util.NetworkObserver
import ir.romina.reza.core.util.NetworkStatus
import ir.romina.reza.core.util.Resource
import ir.romina.reza.feature_map_screen.domain.use_case.GetStationsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val networkObserver: NetworkObserver
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {

        getStations()
    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnEnteredSearchText -> {
                state = state.copy(
                    searchText = event.text
                )
            }
            is SearchScreenEvent.OnStationDetailClicked -> {
                viewModelScope.launch {
                    _uiEvent.emit(
                        UiEvent.Navigate(
                            Route.StationDetailScreen + "capacity=${event.station.capacity}"
                        )
                    )
                }
            }
        }
    }

    private fun getStations() {
        state = state.copy(
            loading = true
        )
        viewModelScope.launch {
            networkObserver.networkStatus.collectLatest { status ->
                when (val result = getStationsUseCase(status)) {
                    is Resource.Error -> {
                        state = state.copy(
                            loading =  false
                        )
                        _uiEvent.emit(
                            UiEvent.ShowSnackbar(
                                result.uiText ?: UiText.StringResource(
                                    R.string.unknownError
                                )
                            )
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            loading =  false,
                            stations = result.data ?: emptyList()
                        )

                    }
                }
            }
        }
    }


}