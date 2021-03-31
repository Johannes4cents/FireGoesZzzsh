package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs

class Helper(context: Context, attrs: AttributeSet): FireIceButton(context, attrs){
    var owner = MutableLiveData<String>("")
    var imgSrc = 0
    var imgSrcSelected = 0

    init {
        context.obtainStyledAttributes(attrs, R.styleable.Helper).apply {
            try {
                type = getString(R.styleable.Helper_whatHelper).toString()
            }   finally {
                recycle()
            } }
        setImageResource(getImageSRC())
        clickListener()
        obsPlayer()
        d.game.helperListenToGame(this)
    }

    private fun clickListener() {
        d.game.pickHelper(this) { helperChanged ->
            if(helperChanged) {
                setImageResource(getImageSelected())
                d.player.helper = this
            }
        }
    }

    private fun obsPlayer() {
        owner.observe(context as LifecycleOwner, { owner ->
            if(owner != d.fireUser?.uid && owner != "") {
                UtilFuncs.disable(this)
            }
            else {
                UtilFuncs.enable(this)
            }
        })
    }

}