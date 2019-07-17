package id.aasumitro.firegate.util

/**
 * Created by A. A. Sumitro on 27/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

interface NetworkInterface {

    fun onSuccess(message: String)

    fun onError(message: String)

    fun onInfo(message: String)

    fun onAlert(message: String, actionText: String, actionListener: Runnable)

    fun onProgress(isShow: Boolean = true)

}