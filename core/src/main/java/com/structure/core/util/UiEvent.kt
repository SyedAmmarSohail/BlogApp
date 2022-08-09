package com.structure.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowToast(val message: UiText): UiEvent()
}
