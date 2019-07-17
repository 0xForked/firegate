package id.aasumitro.firegate.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.firegate.R
import id.aasumitro.firegate.data.model.SMS

/**
 * Created by A. A. Sumitro on 09/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FiregateAdapter(
    private val mData:  ArrayList<SMS>,
    private val mListener: FiregateListener<SMS>
) : RecyclerView.Adapter<FiregateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiregateViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_data, parent, false)
        return FiregateViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FiregateViewHolder,
        position: Int
    ) = holder.bind(mData[position], mListener)

    override fun getItemCount(): Int = mData.size
}