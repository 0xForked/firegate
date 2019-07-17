package id.aasumitro.firegate.util

import android.content.Context
import android.graphics.Typeface
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import id.aasumitro.firegate.R

/**
 * Created by A. A. Sumitro on 27/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

fun showSnackBar(
    context: Context,
    layoutParent: View,
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String = "",
    actionListener: Runnable? = null
) {
    val mSnackBar = Snackbar.make(layoutParent, message, duration)
    if(message.contains("Unable to resolve")) mSnackBar.behavior = NoSwipe()
    mSnackBar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
    val textViewAction =
        mSnackBar.view.findViewById(
            com.google.android.material.R.id.snackbar_action
        ) as TextView
    val textView = mSnackBar.view.findViewById(
        com.google.android.material.R.id.snackbar_text
    ) as TextView
    textView.setTypeface(Typeface.createFromAsset(
        context.assets,
        Constants.View.Name[0] + Constants.View.Style[0] + Constants.View.Type),
        Typeface.NORMAL
    )
    textView.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight))
    textView.gravity = Gravity.CENTER
    textView.textSize = 11.5f
    textView.maxLines = 2
    if(actionText != "") {
        textViewAction.setTypeface(Typeface.createFromAsset(
            context.assets,
            Constants.View.Name[0] + Constants.View.Style[1] + Constants.View.Type
        ), Typeface.NORMAL)
        textViewAction.setTextColor(
            ContextCompat.getColor(context, R.color.colorTextPrimary))
        textViewAction.gravity = Gravity.CENTER
        textViewAction.textSize = 11.5f
        mSnackBar.setAction(actionText) { actionListener?.run() }
    }
    Handler().postDelayed({
        mSnackBar.show()
    }, (if(actionText != "") 1000 else 0).toLong())
}