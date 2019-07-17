package id.aasumitro.firegate.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.aasumitro.firegate.data.FiregateRepositoryImpl
import id.aasumitro.firegate.data.model.SMS
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

/**
 * Created by A. A. Sumitro on 15/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MainViewModel : BaseViewModel() {

    private val mRepository = FiregateRepositoryImpl()

    private val realtimeCollectionResult: MutableLiveData<ArrayList<SMS>> by lazy {
        MutableLiveData<ArrayList<SMS>>().also { watchCollection() }
    } // get result in realtime

    fun listenToCollections(): LiveData<ArrayList<SMS>> = realtimeCollectionResult

    private fun watchCollection() {
        uiScope.launch {
            mRepository
                .watchCollections()
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w(FiregateRepositoryImpl.TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }
                    (value?.size() == 0).let { Log.w(FiregateRepositoryImpl.TAG, "No data available", e) }
                    realtimeCollectionResult.value = value?.toObjects(SMS::class.java) as ArrayList<SMS>
                }
        }
    }

}
