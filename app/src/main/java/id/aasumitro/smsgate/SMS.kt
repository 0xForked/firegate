package id.aasumitro.smsgate

import com.google.firebase.Timestamp

/**
 * Created by A. A. Sumitro on 09/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

data class SMS (
    val id: String? = null,
    val number: String? = null,
    val message: String? = null,
    val status: String? = null,
    val provider: String? = null,
    val issued: Timestamp? = null,
    val sent: Timestamp? = null
)