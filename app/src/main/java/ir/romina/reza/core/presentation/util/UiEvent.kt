package ir.romina.reza.core.presentation.util

sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText, val action: String? = null) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}