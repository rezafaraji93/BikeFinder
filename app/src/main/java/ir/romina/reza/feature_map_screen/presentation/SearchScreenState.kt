package ir.romina.reza.feature_map_screen.presentation

import ir.romina.reza.feature_map_screen.domain.model.Station

data class SearchScreenState(
    val stations: List<Station> = emptyList(),
    val loading: Boolean = true,
    val searchText: String = ""
)
