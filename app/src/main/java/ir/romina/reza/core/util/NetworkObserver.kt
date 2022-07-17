package ir.romina.reza.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkObserver(
    context: Context
) {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow {

        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            connectivityManager.activeNetworkInfo
        }

        if (activeNetwork == null) {
            trySend(NetworkStatus.UnAvailable).isSuccess
        }

        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(NetworkStatus.Available).isSuccess
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(NetworkStatus.Lost).isSuccess
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(NetworkStatus.UnAvailable).isSuccess
            }

        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }

    }

}