package com.xavier667.sktunnel

import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream

class SKTunnelService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startVpn()
        return START_STICKY
    }

    private fun startVpn() {
        val builder = Builder()
            .setSession("SK Tunnel")
            .setMtu(1500)
            .addAddress("10.0.0.2", 32)
            .addDnsServer("8.8.8.8")
            .addRoute("0.0.0.0", 0)
            .allowBypass()   // Important for Android

        vpnInterface = builder.establish()

        // TODO: Here you will route traffic to different protocol handlers
        // (WireGuard, sing-box, OpenVPN library, etc.)
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}