package id.aasumitro.firegate.ui.rv

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.firegate.data.model.SMS
import id.aasumitro.firegate.util.Constants.Text.FAILED
import id.aasumitro.firegate.util.Constants.Text.PROCESS
import id.aasumitro.firegate.util.Constants.Text.SENT
import kotlinx.android.synthetic.main.item_data.view.*


/**
 * Created by A. A. Sumitro on 09/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FiregateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: SMS, listener: FiregateListener<SMS>) = with(itemView) {
        sms_number.text = data.number
        when (data.status) {
            PROCESS -> {
                sms_status.setTextColor(Color.parseColor("#CECECE"))
                val issued = "Issued on: ${data.issued}"
                sms_date.text = issued
            }
            SENT -> {
                sms_status.setTextColor(Color.parseColor("#0AD48B"))
                val sent = "Sent on: ${data.sent}"
                sms_date.text = sent
            }
            FAILED -> {
                sms_status.setTextColor(Color.parseColor("#BA0000"))
                sms_date.visibility = View.GONE
            }
        }
        sms_status.text = data.status
        sms_container.setOnClickListener { listener.onClickListener(data) }
    }

}