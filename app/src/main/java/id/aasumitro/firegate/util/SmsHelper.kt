package id.aasumitro.firegate.util

import android.telephony.SmsManager

/**
 * Created by A. A. Sumitro on 17/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

fun sendSMS(number: String?, message: String?) {
    val smsManager = SmsManager.getDefault()
    smsManager.sendTextMessage(number, null, message, null, null)
}