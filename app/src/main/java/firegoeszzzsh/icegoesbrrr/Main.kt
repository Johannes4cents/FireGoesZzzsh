package firegoeszzzsh.icegoesbrrr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import firegoeszzzsh.icegoesbrrr.classes.Game
import firegoeszzzsh.icegoesbrrr.classes.MagicQueue
import firegoeszzzsh.icegoesbrrr.classes.Player
import firegoeszzzsh.icegoesbrrr.fragment.GameFragment


object d {
    lateinit var imm : InputMethodManager
    lateinit var magicQueue: MagicQueue
    lateinit var gameFragment: GameFragment
    val player = Player()
    var db = FirebaseFirestore.getInstance()
    val mAuth = FirebaseAuth.getInstance()
    var fireUser : FirebaseUser? = null
    lateinit var game : Game
}

object misc {
    var nicktNameTextView: TextView? = null
}

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        d.game = Game()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }
}