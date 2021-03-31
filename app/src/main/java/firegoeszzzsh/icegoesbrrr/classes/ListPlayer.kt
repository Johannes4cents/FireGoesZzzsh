package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import firegoeszzzsh.icegoesbrrr.databinding.ListPlayerBinding

class ListPlayer(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs) {
    val b = ListPlayerBinding.inflate(LayoutInflater.from(context), null, false)

    lateinit var player : Player

    fun getPlayerInfo() {
        b.playerNameInLinear.text = player.nickName
        player.playerChangeTrigger.observe(context as LifecycleOwner, {
            b.playerNameInLinear.text = player.nickName ?: "Unknown player"
            Log.i("testen", "playerNameInLinear = ${b.playerNameInLinear.text}")
            if(player.hatClass != null) {
                b.hatIcon.type = player.hatClass?.type ?: ""
                b.hatIcon.getImageSRC()
            }
            if(player.host) b.host.visibility = View.VISIBLE
            invalidate()
        })

    }


}