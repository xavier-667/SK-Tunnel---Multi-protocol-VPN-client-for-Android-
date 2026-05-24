package com.surprisekhoza.sktunnel

import android.net.VpnService
import android.content.Intent
import android.os.ParcelFileDescriptor

class SktunnelVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startVpn()

        return START_STICKY
    }

    private fun startVpn() {
        val builder = Builder()

        builder.setSession("SKTunnel VPN")
            .addAddress("10.10.0.2", 32)
            .addRoute("0.0.0.0", 0)

        vpnInterface = builder.establish()

        // NEXT STEP: attach sing-box core here
    }

    override fun onDestroy() {
        vpnInterface?.close()
        vpnInterface = null
        super.onDestroy()
    }
}