package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import firegoeszzzsh.icegoesbrrr.d.player
import firegoeszzzsh.icegoesbrrr.databinding.ScreenBinding

class Screen(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    val b = ScreenBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setClickListener()
        observeChosenAction()
    }

    fun actionInPlay() {
    }

    private fun setClickListener() {
        b.btnActionDone.setOnClickListener {
            player.chosenAction.value = null
            it.visibility = View.GONE
        }
    }

    private fun observeChosenAction() {
        player.chosenAction.observe(context as LifecycleOwner, {
            b.btnActionDone.visibility = if(it == null) View.GONE else View.VISIBLE
            when(it?.type) {
                "endShift" -> {}
                "banishUnlock" -> {}
                "cast" -> {}
                "charge" -> {}
                "discard" -> {}
                "focus" -> {}
                "move" -> {}
                "startShift" -> {}
                "throwDice" -> {}
                "trade" -> {}
                "use" -> {}
                "useStrength" -> {}
            }
        })
    }
}
