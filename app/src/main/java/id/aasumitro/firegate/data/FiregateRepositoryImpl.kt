package id.aasumitro.firegate.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

/**
 * Created by A. A. Sumitro on 15/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FiregateRepositoryImpl {

    val mFirestore =
        FirebaseFirestore.getInstance()

    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(false)
        .build()

    fun watchCollections() = mFirestore.collection(COLLECTION)

    fun updateCollection(
        id: String,
        item: Map<String, Any>
    ) = mFirestore.collection(COLLECTION).document(id).update(item)

    companion object {
        const val TAG = "FIREGATE_REPOSITORY"
        const val COLLECTION = "sms"
    }

}