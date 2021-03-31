package firegoeszzzsh.icegoesbrrr.classes

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d.player
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs.makeBlackWhite
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs.enable


@SuppressLint("AppCompatCustomView")
class Action(context: Context, attrs: AttributeSet): FireIceButton(context, attrs) {

    init {
        context.obtainStyledAttributes(attrs, R.styleable.Action).apply {
            try{
            type = getString(R.styleable.Action_actionType).toString()
        } finally {
            recycle()
        }
        }
        setImageResource(getImageSRC())
        setClickListener()
        checkIfChosen()
    }

    private fun setClickListener() {
        setOnClickListener {
            if ( player.chosenAction.value == null) {
                player.chosenAction.value = this
            }
        }
    }


    private fun checkIfChosen() {
        player.chosenAction.observe(context as LifecycleOwner, {
            if(it == this) {
                enable(this)
            }
            else {
                if(it == null){enable(this)} else makeBlackWhite(this)}
        }
        )
    }
}


