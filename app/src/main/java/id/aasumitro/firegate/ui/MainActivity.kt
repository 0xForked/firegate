package id.aasumitro.firegate.ui

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.firegate.R
import id.aasumitro.firegate.base.BaseActivity
import id.aasumitro.firegate.data.model.SMS
import id.aasumitro.firegate.service.FiregateSenderService
import id.aasumitro.firegate.ui.rv.FiregateAdapter
import id.aasumitro.firegate.ui.rv.FiregateListener
import id.aasumitro.firegate.ui.vm.MainViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity(R.layout.activity_main), FiregateListener<SMS> {

    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val PERMISSION_REQUEST_CODE = 1
        private const val smsPermission = Manifest.permission.SEND_SMS
    }

    private lateinit var mAdapter: FiregateAdapter

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(MainViewModel::class.java)
    }

    override fun initView() {
        initToolbar(pageName = "Firegate")
        initRecyclerView()
        //requestPermissionsIfNecessary()
        mViewModel.listenToCollections().observe(this, collection)
        val firegateSenderService = Intent(this, FiregateSenderService::class.java)
        startService(firegateSenderService)
    }

    private fun initRecyclerView() {
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        sms_recycler_view.layoutManager = mLayoutManager
        sms_recycler_view.itemAnimator = DefaultItemAnimator()
    }

    private val collection = Observer<ArrayList<SMS>> { data ->
        onProgress(true)
        data.let {
            mAdapter = FiregateAdapter(data, this)
            mAdapter.notifyDataSetChanged()
            sms_recycler_view.adapter = mAdapter
            onProgress(false)
        }
    }

    override fun onClickListener(data: SMS) {
        Toast.makeText(
            this,
            "send sms with message { ${data.message} } to { ${data.number} }",
            Toast.LENGTH_LONG
        ).show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        val hasSendSMSPermission = checkSelfPermission(smsPermission)
        if (hasSendSMSPermission == PackageManager.PERMISSION_DENIED) {
            val permissionRequested = arrayOf(smsPermission)
            requestPermissions(
                permissionRequested,
                PERMISSION_REQUEST_CODE
            )
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    private fun requestPermissionsIfNecessary() = when (checkPermissionState(this, locationPermission)) {
//        PermissionState.GRANTED -> {
//        }
//        PermissionState.NOT_GRANTED -> requestPermission(this, locationPermission)
//        PermissionState.REJECTED -> {
//            firebaseAnalytics.logEvent("permissions_are_rejected", null)
//            askNicelyForPermissions()
//        }
//    }

//    private fun askNicelyForPermissions(onDismiss: (() -> Unit)? = null) = AlertDialog.Builder(this)
//        .setTitle(resources.getString(R.string.permissions_denied))
//        .setMessage(resources.getString(R.string.cannot_run_without_location_permissions))
//        .setNeutralButton("OK") { dialog, _ ->
//            dialog.dismiss()
//            onDismiss?.invoke() ?: requestPermission(this, locationPermission)
//        }
//        .create()
//        .show()

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            PERMISSION_REQUEST_CODE -> {
//                if ((grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, locationPermission)) {
//                        askNicelyForPermissions()
//                    } else {
//                        askNicelyForPermissions { navigateToSettings() }
//                    }
//                }
//            }
//            else -> throw IllegalArgumentException("Invalid request code!")
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        when (requestCode) {
//            SETTINGS_ACTIVITY_CODE -> requestPermissionsIfNecessary()
//            else -> throw IllegalArgumentException("Invalid request code!")
//        }
//    }

}