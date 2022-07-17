package ir.romina.reza.feature_map_screen.data.repository

import ir.romina.reza.R
import ir.romina.reza.core.util.Resource
import ir.romina.reza.core.presentation.util.UiText
import ir.romina.reza.feature_map_screen.data.local.StationsDao
import ir.romina.reza.feature_map_screen.data.mapper.toStation
import ir.romina.reza.feature_map_screen.data.remote.StationsApi
import ir.romina.reza.feature_map_screen.domain.model.Station
import ir.romina.reza.feature_map_screen.domain.repository.StationRepository
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.lang.Exception

class StationRepositoryImpl(
    private val api: StationsApi,
    private val dao: StationsDao
): StationRepository {

    override suspend fun getStationsData(): Resource<List<Station>> {
        return try {
            val response = api.getStationsData()
            val stations = response.map { it.toStation() }
            dao.insertAllStations(stations)
            Resource.Success(stations)
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.StringResource(
                    R.string.unknownError
                )
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(
                    R.string.connectionError
                )
            )
        }
    }

    override suspend fun getStationsFromDb(): Resource<List<Station>> {
        return Resource.Success(data = dao.getStations().first())
    }


}