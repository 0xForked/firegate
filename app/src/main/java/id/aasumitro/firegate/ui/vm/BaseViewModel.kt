package id.aasumitro.firegate.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by A. A. Sumitro on 15/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

abstract class BaseViewModel : ViewModel() {

    private val viewModelJob = Job()

    val uiScope = CoroutineScope(
        Dispatchers.Main + viewModelJob
    )

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}