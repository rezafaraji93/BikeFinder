package ir.romina.reza.feature_map_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.romina.reza.feature_map_screen.domain.model.Station

@Database(
    entities = [Station::class],
    version = 1
)
abstract class StationsDatabase: RoomDatabase() {

    abstract val dao: StationsDao

    companion object {
        const val DATABASE_NAME = "stationsDb"
    }

}