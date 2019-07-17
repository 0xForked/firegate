package id.aasumitro.firegate.util

import android.graphics.PorterDuff
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.os.Build
import android.graphics.drawable.Drawable

/**
 * Created by A. A. Sumitro on 16/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object MyDrawableCompat {

    fun setColorFilter(drawable: Drawable?, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable?.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

}