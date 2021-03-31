package firegoeszzzsh.icegoesbrrr.utility

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import firegoeszzzsh.icegoesbrrr.d

object UtilFuncs {
    val colorMatrix = ColorMatrix()

    fun unfilteredInput(edit: EditText, func: (text: String) -> Unit) {
        edit.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                func(edit.text.toString())
                return@OnKeyListener true
            }
            false
        })

        edit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                func(edit.text.toString())
            }
        }
    }
    
    fun createGamecode(): String {
        val chars= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        return "" + chars.random() + chars.random() + chars.random() + chars.random()
    }

    fun checkInput(edit: EditText, func : (text: String) -> Unit) {
        edit.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                func(edit.text.toString().toLowerCase().capitalize())
                return@OnKeyListener true
            }
            false
        })

        edit.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                func(edit.text.toString().toLowerCase().capitalize())
            }
        }
    }


    fun makeBlackWhite(v: ImageView){
        colorMatrix.setSaturation(0F)
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        v.setColorFilter(colorFilter)
    }

    fun enable(v: ImageView) {
        colorMatrix.setSaturation(1F)
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        v.setColorFilter(colorFilter)
        v.alpha = 1f
        v.isEnabled = true
        v.invalidate()
    }

    fun disable(v: ImageView) {
        makeBlackWhite(v)
        v.alpha = 0.5f
        v.isEnabled = false
        v.invalidate()
    }


}