package ir.romina.reza.feature_map_screen.data.remote.response

data class StationDto(
    val capacity: String,
    val geocoded_column: GeocodedColumn,
    val lat: String,
    val lon: String,
    val name: String,
    val rental_method: String,
    val station_id: String
)