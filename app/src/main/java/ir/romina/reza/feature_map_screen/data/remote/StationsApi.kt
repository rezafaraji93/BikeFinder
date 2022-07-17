package ir.romina.reza.feature_map_screen.data.remote

import ir.romina.reza.core.util.Resource
import ir.romina.reza.feature_map_screen.data.remote.response.StationDto
import retrofit2.http.GET

interface StationsApi {

    @GET("/resource/vrwc-rwgm.json")
    suspend fun getStationsData(): List<StationDto>

    companion object {
        const val BASE_URL = "https://data.melbourne.vic.gov.au"
    }

}