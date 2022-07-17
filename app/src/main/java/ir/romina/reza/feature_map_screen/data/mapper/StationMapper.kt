package ir.romina.reza.feature_map_screen.data.mapper

import ir.romina.reza.feature_map_screen.data.remote.response.StationDto
import ir.romina.reza.feature_map_screen.domain.model.Station

fun StationDto.toStation(): Station {
    return Station(
        capacity = capacity,
        lat = lat.toDouble(),
        lng = lon.toDouble(),
        name = name,
        rental_method = rental_method,
        station_id = station_id
    )
}