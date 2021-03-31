package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d

class Hat(context: Context, attrs: AttributeSet): FireIceButton(context, attrs) {
    companion object {
        var inLobby: Boolean = true
        val selectedHat = MutableLiveData<Hat>() }
    var imgSrc= 0

    init {
        context.obtainStyledAttributes(attrs, R.styleable.Hat).apply {
            try {
                type = getString(R.styleable.AppButton_btnType).toString()
                setImageResource(getImageSRC())
            } finally {
                recycle()
            }
        }
        setClickListener()
        obsSelected()
    }

    private fun setClickListener() {
        setOnClickListener {
            d.player.hat = this
            d.player.playerChangeTrigger.value = Unit
            if(inLobby) selectedHat.value = this }
    }

    private fun obsSelected() {
        selectedHat.observe(context as LifecycleOwner, {
            if(this == it && inLobby) {
                background.colorFilter = LightingColorFilter(0x77777777, 0x77777777)
                invalidate()
            } else {background.colorFilter = LightingColorFilter(0xFFFFFFFF.toInt(), 0); invalidate()}
        })
    }
}