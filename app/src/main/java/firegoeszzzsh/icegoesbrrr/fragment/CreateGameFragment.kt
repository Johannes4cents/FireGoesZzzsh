package firegoeszzzsh.icegoesbrrr.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import firegoeszzzsh.icegoesbrrr.classes.ListPlayer
import firegoeszzzsh.icegoesbrrr.classes.Player
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.CreateGameBinding
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs.checkInput

class CreateGameFragment: Fragment() {
    lateinit var b: CreateGameBinding

    companion object {
        var gameCounter = MutableLiveData<Int>(0)
        var playerList = mutableListOf<Player>()
        var playerListTrigger = MutableLiveData<Unit>()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = CreateGameBinding.inflate(inflater)
        gameCounter.observe(context as LifecycleOwner, {
            b.playerCounter.text = it.toString()
        })


        clickListener(b.enterNickInput, b.nickNameTextView)
        observer()
        handleIncomingPlayer()
        return b.root
    }

    private fun handleIncomingPlayer(){
        playerList.add(d.player)
        playerListTrigger.value = Unit
    }

    private fun observer() {
        playerListTrigger.observe(context as LifecycleOwner, {
            b.playerNameLinear.removeAllViews()
            playerList.forEach {
                val listPlayer = ListPlayer(requireContext(), null)
                listPlayer.player = it
                listPlayer.getPlayerInfo()
                b.playerNameLinear.addView(listPlayer)
                gameCounter.value = gameCounter.value?.plus(1)
            }
        })
    }
    private fun clickListener(e: EditText, t: TextView) {
        t.setOnClickListener {
            it.visibility = View.GONE
            e.visibility = View.VISIBLE
            e.requestFocus()
            d.imm.showSoftInput(e, 0)
            checkInput(e) {nick: String ->
                d.player.nickName = nick
                t.text = nick
                e.visibility = View.GONE
                t.visibility = View.VISIBLE
                t.setTextColor(Color.BLACK)
                d.imm.hideSoftInputFromWindow(e.windowToken, 0)
                d.player.playerChangeTrigger.value = Unit
            }
        }
    }
}