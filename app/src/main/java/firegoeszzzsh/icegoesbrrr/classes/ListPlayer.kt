package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.ListPlayerBinding

class ListPlayer(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs) {
    val b = ListPlayerBinding.inflate(LayoutInflater.from(context), null, false)

    lateinit var player : Player

    fun getPlayerInfo() {
        b.playerNameInLinear.text = player.nickName
        player.playerChangeTrigger.observe(context as LifecycleOwner, {
            if(player.hat != null) {
                b.hatIcon.setImageResource(player.hat?.imgSrc!!)
            }
            if(player.host) b.host.visibility = View.VISIBLE
        })

    }


}