package ru.nikolyashka.breakingbadsample.core

import ru.nikolyashka.core.NetworkManager
import java.net.InetAddress

class NetworkManagerImpl : NetworkManager {

    override fun isInternetAvailable(): Boolean {
        return try {
            val address: InetAddress = InetAddress.getByName(GOOGLE_ADDRESS)
            !address.equals("")
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val GOOGLE_ADDRESS = "www.google.com"
    }
}