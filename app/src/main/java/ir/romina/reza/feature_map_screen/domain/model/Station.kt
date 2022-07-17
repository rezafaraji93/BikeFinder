package ir.romina.reza.feature_map_screen.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "stations")
data class Station(
    val capacity: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val rental_method: String,
    @PrimaryKey val station_id: String
)
