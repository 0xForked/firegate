package id.aasumitro.firegate.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Source
import com.google.firebase.iid.FirebaseInstanceId
import id.aasumitro.firegate.data.FiregateRepositoryImpl
import id.aasumitro.firegate.data.model.SMS
import id.aasumitro.firegate.util.Constants.Text.PROCESS
import id.aasumitro.firegate.util.Constants.Text.SENT
import id.aasumitro.firegate.util.sendSMS
import id.aasumitro.firegate.util.showNotification
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by A. A. Sumitro on 15/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FiregateSenderService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "service starting") // debug to check service is running/on starting
        return START_STICKY  // If we get killed, after returning from here, restart
    }

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        val mRepository = FiregateRepositoryImpl()
        mRepository.mFirestore.apply {
            firestoreSettings =
                mRepository.settings
        }

        mRepository
            .watchCollections()
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(FiregateRepositoryImpl.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                (snapshot?.size() == 0).let { Log.w(FiregateRepositoryImpl.TAG, "No data available", e) }
                val data = snapshot?.toObjects(SMS::class.java) as ArrayList<SMS>
                if (data.size > 0) Log.d(TAG, data.toString())
                data.forEachIndexed { index, sms ->
                    if (sms.status.equals(PROCESS)) {
                        sendSMS(sms.number, sms.message).also {
                            val updateData = mapOf(
                                "status" to SENT,
                                "sent" to Timestamp(Date())
                            )
                            mRepository.updateCollection(sms.id as String, updateData)
                                .addOnSuccessListener {
                                    showNotification(
                                        this,
                                        "Firegate",
                                        "Send sms to ${sms.number}",
                                        index+1
                                    )
                                    Log.d(TAG, "Success update data")
                                }.addOnFailureListener { e ->
                                    Log.d(TAG, e.toString())
                                }
                        }
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "service done")
    }

    companion object {
        val TAG = FiregateSenderService::class.java.simpleName
    }

}