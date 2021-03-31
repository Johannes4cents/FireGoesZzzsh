package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.MagicQueueBinding

class MagicQueue(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    val queue = MutableLiveData<MutableList<Player?>>(null)
    val b = MagicQueueBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        orientation = HORIZONTAL
        d.magicQueue = this
    }

    fun addPlayerToQueue(player: Player) {
        player.attachToQueue(this)
        queue.value?.add(player)
    }

    fun removePlayerFromQueue(player: Player) {
        queue.value?.remove(player)
        player.detachFromQueue()
    }

    fun obsQueue() {

    }

}