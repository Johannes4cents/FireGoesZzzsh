package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs

class Hat(context: Context, attrs: AttributeSet): FireIceButton(context, attrs) {
    companion object {
        var inLobby: Boolean = true
        val selectedHat = MutableLiveData<Hat>() }
    var owner = MutableLiveData<String>("")

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
        d.game.hatListenToGame(this)
    }

    private fun setClickListener() {
        setOnClickListener {
            d.game.pickHat(this) {
                if(it) {
                    d.player.hatClass = this
                    d.player.hat = type
                    d.player.playerChangeTrigger.value = Unit
                    selectedHat.value = this
                }
                else { Log.i("testen", "Hat/setClickListener - klappt nicht mit firestore")}
            }
        }
    }

    private fun obsSelected() {
        selectedHat.observe(context as LifecycleOwner, {
            if(this == it && inLobby) {
                background.colorFilter = LightingColorFilter(0x77777777, 0x77777777)
                invalidate()
            } else {background.colorFilter = LightingColorFilter(0xFFFFFFFF.toInt(), 0); invalidate()}
        })
        owner.observe(context as LifecycleOwner, {
            if(it != "" && it != d.fireUser?.uid) UtilFuncs.disable(this)
            else {UtilFuncs.enable(this)}
        })

    }
}