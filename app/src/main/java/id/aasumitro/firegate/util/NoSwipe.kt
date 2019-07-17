package id.aasumitro.firegate.util

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar

/**
 * Created by A. A. Sumitro on 27/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

internal class NoSwipe : BaseTransientBottomBar.Behavior() {
    override fun canSwipeDismissView(child: View): Boolean = false
}