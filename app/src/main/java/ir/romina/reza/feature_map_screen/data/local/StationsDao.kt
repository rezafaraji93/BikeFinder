package ir.romina.reza.feature_map_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.romina.reza.feature_map_screen.domain.model.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStations(stations: List<Station>)

    @Query("SELECT * FROM stations")
    fun getStations(): Flow<List<Station>>

    @Query("DELETE FROM stations")
    suspend fun deleteStations()

    @Query("SELECT * FROM stations WHERE station_id = :stationId")
    fun getStationData(stationId: String): Flow<Station>

}