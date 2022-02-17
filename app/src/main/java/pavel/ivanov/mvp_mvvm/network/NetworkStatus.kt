package pavel.ivanov.mvp_mvvm.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(context: Context) {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!
    private val networkStatusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getNetworkSubject(): Observable<Boolean> {
        return networkStatusSubject
    }

    fun isOnline() = networkStatusSubject.value ?: false

    init {
        val request = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            /** Сеть есть уже сейчас */
            override fun onAvailable(network: Network) {
                networkStatusSubject.onNext(true)
            }

            /** Сеть потеряна */
            override fun onLost(network: Network) {
                networkStatusSubject.onNext(false)
            }

            /** Сеть не обнаружена после запроса */
            override fun onUnavailable() {
                networkStatusSubject.onNext(false)
            }
        })
    }
}