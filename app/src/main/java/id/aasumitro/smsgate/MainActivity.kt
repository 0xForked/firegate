package id.aasumitro.smsgate

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), SMSListener {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var mDatabase: FirebaseFirestore
    private lateinit var mAdapter: SMSAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mDatabase = FirebaseFirestore.getInstance()

        initRecyclerView()
        initAdapter()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initRecyclerView() {
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        sms_recycler_view.layoutManager = mLayoutManager
        sms_recycler_view.itemAnimator = DefaultItemAnimator()
    }

    private fun initAdapter() {
        mDatabase
            .collection("sms")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val sms = ArrayList<SMS>()
                value?.forEach {
                    it.let { doc ->
                        doc.toObject(SMS::class.java).let { item ->
                            sms.add(item)
                        }
                    }
                }
                mAdapter = SMSAdapter(sms, this)
                mAdapter.notifyDataSetChanged()
                sms_recycler_view.adapter = mAdapter

            }
    }

    override fun onClickListener(data: SMS) {
        Toast.makeText(this, "onSend: ${data.number}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("SMSStatusPermission", "Permission Granted")
        } else {
            Log.d("SMSStatusPermission", "Permission denied")
        }
    }
}
