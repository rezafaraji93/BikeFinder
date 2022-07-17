package ir.romina.reza.feature_map_screen.presentation

import ir.romina.reza.feature_map_screen.domain.model.Station

sealed class SearchScreenEvent {
    data class OnStationDetailClicked(val station: Station) : SearchScreenEvent()
    data class OnEnteredSearchText(val text: String) : SearchScreenEvent()
}
