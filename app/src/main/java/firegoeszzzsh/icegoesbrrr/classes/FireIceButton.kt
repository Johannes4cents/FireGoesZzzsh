package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import firegoeszzzsh.icegoesbrrr.R


open class FireIceButton(context: Context, attributeSet: AttributeSet): AppCompatImageView(context, attributeSet) {
    var type: String = ""

    fun getImageSRC(): Int {
        return when(type) {
            "baby" -> { R.drawable.h_baby }
            "butler" -> { R.drawable.h_butler }
            "dragon" -> { R.drawable.h_dragon }
            "michelin" -> { R.drawable.h_michelin }
            "girl" -> { R.drawable.h_girl }
            "zac" -> { R.drawable.h_zac }
            "throwDice" -> { R.drawable.a_wuerfeln }
            "charge" -> { R.drawable.a_stab_aufladen }
            "cast" -> { R.drawable.a_zaubern }
            "use" -> { R.drawable.a_benutzen }
            "move" -> { R.drawable.a_bewegen }
            "discard" -> { R.drawable.a_abwerfen }
            "focus" -> { R.drawable.a_fokussieren }
            "trade" -> { R.drawable.a_handeln }
            "useStrength" -> { R.drawable.a_kraft_anwenden }
            "startShift" -> { R.drawable.a_schicht_beginnen }
            "take" -> { R.drawable.a_nehmen }
            "endShift" -> { R.drawable.a_schicht_beenden }
            "pink" -> {R.drawable.pink_hat}
            "red" -> {R.drawable.red_hat}
            "blue" -> {R.drawable.blue_hat}
            "green" -> {R.drawable.green_hat}
            "black" -> {R.drawable.black_hat}
            "gold" -> {R.drawable.yellow_hat}
            else -> 0
        }

    }

    fun getImageSelected(): Int {
        return when(type) {
            "baby" -> { R.drawable.h_baby_selected }
            "butler" -> { R.drawable.h_butler_selected }
            "dragon" -> { R.drawable.h_dragon_selected }
            "michelin" -> { R.drawable.h_michelin_selected }
            "girl" -> { R.drawable.h_girl_selected }
            "zac" -> { R.drawable.h_zac_selected }
            else -> 0
        }
    }
}