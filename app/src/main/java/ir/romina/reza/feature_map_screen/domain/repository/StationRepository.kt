package ir.romina.reza.feature_map_screen.domain.repository

import ir.romina.reza.core.util.Resource
import ir.romina.reza.feature_map_screen.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    suspend fun getStationsData(): Resource<List<Station>>

    suspend fun getStationsFromDb(): Resource<List<Station>>

    fun getStationFromDb(stationId: String): Flow<Station>

}