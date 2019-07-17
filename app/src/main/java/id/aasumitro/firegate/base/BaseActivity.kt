package id.aasumitro.firegate.base

import android.content.Intent
import android.content.IntentFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import id.aasumitro.firegate.R
import id.aasumitro.firegate.service.ConnectionReceiver
import id.aasumitro.firegate.service.FiregateSenderService
import id.aasumitro.firegate.util.ConnectionInterface
import id.aasumitro.firegate.util.MyDrawableCompat
import id.aasumitro.firegate.util.NetworkInterface
import id.aasumitro.firegate.util.showSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_loading.*
import kotlinx.android.synthetic.main.component_toolbar.*

/**
 * Created by A. A. Sumitro on 15/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

abstract class BaseActivity(
    @LayoutRes var layout: Int
) : AppCompatActivity(), ConnectionInterface, NetworkInterface {

    companion object {
        const val TAG = "BASE_ACTIVITY"
    }

    private var mConnectionReceiver: ConnectionReceiver? = null

    private var isRegisteredReceiver: Boolean = false

    protected abstract fun initView()

    protected open fun initListener() {}

    fun initToolbar(
        back: Boolean = false,
        pageName: String = "",
        primary: Boolean = false
    ) {
        setSupportActionBar(toolbar)
        setPageName(pageName)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(back)

            val backIcon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_chevron_left_blue_24dp
            ).also {
                MyDrawableCompat.setColorFilter(it, ContextCompat.getColor(
                    this,
                    R.color.colorTextAccent
                ))
            }

            val iconColor = PorterDuffColorFilter(ContextCompat.getColor(
                this,
                if(primary)
                    R.color.colorTextAccent
                else
                    R.color.colorTextPrimary
            ), PorterDuff.Mode.MULTIPLY)

            backIcon?.let {
                it.colorFilter = iconColor
                toolbar.overflowIcon?.colorFilter = iconColor
            }

            actionBar.setHomeAsUpIndicator(backIcon)
        }
    }

    private fun setPageName(
        pageName: String
    ) {
        toolbar_title.text = pageName
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isRegisteredReceiver) {
            unregisterReceiver(mConnectionReceiver)
            isRegisteredReceiver = false
        }
        //val firegateSenderService = Intent(this, FiregateSenderService::class.java)
        //stopService(firegateSenderService)
    }

    override fun onPause() {
        super.onPause()
        if(isRegisteredReceiver) {
            unregisterReceiver(mConnectionReceiver)
            isRegisteredReceiver = false
        }
    }

    private fun initData(receiver: ConnectionInterface) {
        if(mConnectionReceiver == null) {
            mConnectionReceiver = ConnectionReceiver()
            mConnectionReceiver?.let {
                val mIntentFilter = IntentFilter()
                it.registerReceiver(receiver)
                mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
                registerReceiver(mConnectionReceiver, mIntentFilter)
                isRegisteredReceiver = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initData(this)
        super.onCreate(savedInstanceState)
        setContentView(layout)
        initView()
        initListener()
        val firegateSenderService = Intent(this, FiregateSenderService::class.java)
        startService(firegateSenderService)
    }

    override fun onProgress(isShow: Boolean) {
        progress_dialog?.let {
            if(isShow) {
                it.visibility = View.VISIBLE
            } else {
                it.visibility = View.GONE
            }
        }
    }

    override fun onConnectionChange(message: String) {
        Log.e(TAG, message)
        if (message != "Internet Connected") {
            container?.let { showSnackBar(this, it, message, Snackbar.LENGTH_LONG) }
        }
    }

    override fun onAlert(message: String, actionText: String, actionListener: Runnable) {
        onProgress(false)
        container?.let { showSnackBar(this, it, message, Snackbar.LENGTH_INDEFINITE, actionText, actionListener) }
    }

    override fun onError(message: String) {
        onProgress(false)
        container?.let { showSnackBar(this, it, message) }
    }

    override fun onSuccess(message: String) {
        onProgress(false)
        container?.let { showSnackBar(this, it, message, Snackbar.LENGTH_LONG) }
    }

    override fun onInfo(message: String) {
        onProgress(false)
        container?.let { showSnackBar(this, it, message) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) { finish() }
        return super.onOptionsItemSelected(item as MenuItem)
    }

}
