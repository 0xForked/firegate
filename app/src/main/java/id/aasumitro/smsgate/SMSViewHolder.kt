package id.aasumitro.smsgate

import android.telephony.SmsManager
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data.view.*


/**
 * Created by A. A. Sumitro on 09/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class SMSViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: SMS, listener: SMSListener) = with(itemView) {
        sms_number.text = data.number
        sms_text.text = data.message
        sms_status.text = data.status
        sms_container.setOnClickListener { listener.onClickListener(data) }

        if (data.number != null && data.status.equals("PROCESS")) {
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    data.number,
                    null,
                    data.message,
                    null,
                    null
                )
               Log.d("SMSStatus", "sent")
            } catch (e: Exception) {
                Log.d("SMSStatus", "error: ${e.message.toString()}")
                e.printStackTrace()
            }
        }
    }

}