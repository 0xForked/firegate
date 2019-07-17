package id.aasumitro.firegate.service

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by A. A. Sumitro on 17/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FiregateAutostartReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context, FiregateSenderService::class.java))
    }

}