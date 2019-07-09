package id.aasumitro.smsgate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by A. A. Sumitro on 09/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class SMSAdapter(
    private val mData:  ArrayList<SMS>,
    private val mListener: SMSListener
) : RecyclerView.Adapter<SMSViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SMSViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_data, parent, false)
        return SMSViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SMSViewHolder,
        position: Int
    ) = holder.bind(mData[position], mListener)

    override fun getItemCount(): Int = mData.size
}