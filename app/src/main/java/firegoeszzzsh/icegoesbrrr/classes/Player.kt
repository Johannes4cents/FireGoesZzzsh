package firegoeszzzsh.icegoesbrrr.classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.PlayerBinding


class Player(
        var nickName: String? = null,
        var hat: String? = null,
        var helper: String? = null,
        var host: Boolean = false
        )
 {
    var hatClass: Hat? = null
    var helperClass: Helper? = null
    var chosenAction = MutableLiveData<Action?>()
    val playerChangeTrigger = MutableLiveData<Unit>()
    lateinit var b : PlayerBinding

    fun gameStartInit() {
        observeChosenAction()
    }

    fun attachToQueue(v: MagicQueue) {
        b = PlayerBinding.inflate(LayoutInflater.from(d.magicQueue.context), null, false)
        if(d.player.nickName?.length!! > 7) { b.playerName.text = this.nickName?.substring(0, 7) } else {
            b.playerName.text = this.nickName
        }
        (hatClass?.parent as ViewGroup).removeView(hatClass)
        hatClass?.layoutParams?.height = 180
        hatClass?.layoutParams?.width = 180
        b.playerHat.addView(hatClass)
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




