package ir.romina.reza.core.util

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Lost : NetworkStatus()
    object UnAvailable : NetworkStatus()
}