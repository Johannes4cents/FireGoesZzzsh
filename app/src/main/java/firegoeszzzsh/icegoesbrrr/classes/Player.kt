package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.graphics.LightingColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.PlayerBinding


class Player {
    var nickName: String? = null
    var hat: Hat? = null
    var helper: Helper? = null
    var shiftBoss : Boolean = false
    var chosenAction = MutableLiveData<Action?>()
    val playerChangeTrigger = MutableLiveData<Unit>()
    var host: Boolean = false
    lateinit var b : PlayerBinding

    fun gameStartInit() {
        observeChosenAction()
    }

    fun attachToQueue(v: MagicQueue) {
        b = PlayerBinding.inflate(LayoutInflater.from(d.magicQueue.context), null, false)
        if(d.player.nickName?.length!! > 7) { b.playerName.text = nickName?.substring(0, 7) } else {
            b.playerName.text = nickName
        }
        (hat?.parent as ViewGroup).removeView(hat)
        hat?.layoutParams?.height = 180
        hat?.layoutParams?.width = 180
        b.playerHat.addView(hat)
        v.b.queueLinear.addView(b.root)
    }

    fun detachFromQueue() {
        d.magicQueue.b.queueLinear.removeView(b.root)
    }

    private fun observeChosenAction() {
        chosenAction.observe(d.gameFragment.context as LifecycleOwner, {
            if(it != null) { d.magicQueue.addPlayerToQueue(this) }
            else d.magicQueue.removePlayerFromQueue(this)
        })
    }
}




