package ir.romina.reza.feature_station_detail.domain.use_case

import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.domain.repository.StationRepository
import kotlinx.coroutines.flow.Flow

class GetStationDataUseCase(
    private val repository: StationRepository
) {

    operator fun invoke(stationId: String): Flow<Station> {
        return repository.getStationFromDb(stationId)
    }

}