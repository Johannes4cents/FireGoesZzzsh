package firegoeszzzsh.icegoesbrrr.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.classes.Hat
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.d.imm
import firegoeszzzsh.icegoesbrrr.databinding.LobbyBinding
import firegoeszzzsh.icegoesbrrr.firebase.FireUtil
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs


class LobbyFragment: Fragment() {
    lateinit var b: LobbyBinding
    var gameCodeTrigger = MutableLiveData<String?>(null)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = LobbyBinding.inflate(inflater)
        Hat.inLobby = true
        imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        clickListener()
        return b.root
    }

    private fun clickListener() {
        b.btnPartieErstellen.setOnClickListener {
            d.player.host = true
            d.game.create { gameCreated ->
                if(gameCreated) {
                    Toast.makeText(context, "Game created. share the game code ${d.game.code} \n with the other players", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_startScreenFragment_to_createGameFragment) }
                else { Toast.makeText(context, "Something went wrong. \n Game has not been created", Toast.LENGTH_SHORT).show()}
            }

        }
        gameCodeTrigger.observe(context as LifecycleOwner, { gameCode ->
            if(gameCode == null) {
                UtilFuncs.disable(b.btnPartieBeitreten)
                }
            else {
                UtilFuncs.enable(b.btnPartieBeitreten)
                b.btnPartieBeitreten.setOnClickListener {
                        d.game.join() {gameExists ->
                            if(gameExists) { findNavController().navigate(R.id.action_startScreenFragment_to_createGameFragment) }
                            else { Toast.makeText(context, "Game does not exist", Toast.LENGTH_SHORT).show() }
                        }
                    }
                }
        })

        b.gameCodeText.setOnClickListener {
            it.visibility = View.GONE
            b.gameCodeInput.visibility = View.VISIBLE
            if((it as TextView).text != "Enter game code") {b.gameCodeInput.setText(it.text)}
            b.gameCodeInput.requestFocus()
            imm.showSoftInput(b.gameCodeInput, 0)
            UtilFuncs.unfilteredInput(b.gameCodeInput) {
                imm.hideSoftInputFromWindow(b.gameCodeInput.windowToken, 0)
                b.gameCodeText.apply {
                    text = it
                    visibility = View.VISIBLE
                    d.game.code = text.toString()
                    gameCodeTrigger.value = it
                }
                b.gameCodeInput.visibility = View.GONE

            }
        }
    }


}





