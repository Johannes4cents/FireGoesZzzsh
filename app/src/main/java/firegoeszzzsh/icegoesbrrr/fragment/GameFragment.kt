package firegoeszzzsh.icegoesbrrr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import firegoeszzzsh.icegoesbrrr.classes.Hat
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.CreateGameBinding
import firegoeszzzsh.icegoesbrrr.databinding.GameBinding

class GameFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = GameBinding.inflate(inflater)
        Hat.inLobby = false
        d.gameFragment = this
        d.player.gameStartInit()
        return b.root
    }
}