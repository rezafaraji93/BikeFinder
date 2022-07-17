package ir.romina.reza.feature_map_screen.domain.use_case

import ir.romina.reza.R
import ir.romina.reza.core.presentation.util.UiEvent
import ir.romina.reza.core.presentation.util.UiText
import ir.romina.reza.core.util.NetworkStatus
import ir.romina.reza.core.util.Resource
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.domain.repository.StationRepository

class GetStationsUseCase(
    private val repository: StationRepository
) {

    suspend operator fun invoke(networkStatus: NetworkStatus): Resource<List<Station>> {
        return when (networkStatus) {
            NetworkStatus.Available -> repository.getStationsData()
            NetworkStatus.Lost -> Resource.Error(
                UiText.StringResource(
                    R.string.dbIsEmpty
                )
            )
            NetworkStatus.UnAvailable -> {
                val result = repository.getStationsFromDb()
                result.data?.let { stations ->
                    Resource.Success(stations)
                } ?: Resource.Error(
                    UiText.StringResource(
                        R.string.dbIsEmpty
                    )
                )
            }
        }
    }

}