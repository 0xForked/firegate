package id.aasumitro.firegate.util

import id.aasumitro.firegate.R
/**
 * Created by A. A. Sumitro on 16/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


object Constants {

    object View {
        val Style= arrayOf("Regular", "Medium", "MediumItalic", "Bold", "BoldItalic", "Italic")
        val Name= arrayOf("fonts/GoogleSans-")
        val Input= arrayOf(
            R.drawable.input_primary,
            R.drawable.input_accent,
            R.drawable.input_secondary
        )
        const val Type: String = ".ttf"
    }

    object Text {
        const val PROCESS = "PROCESS"
        const val SENT = "SENT"
        const val FAILED = "FAILED"
    }

}