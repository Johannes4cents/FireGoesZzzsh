package firegoeszzzsh.icegoesbrrr.classes

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs

data class Game(
        var code: String? = null,
        var host: String = "",
        var hasStarted: Boolean = false,
        var shiftBoss: String = "",
        var currentPlayer: String = "",
        var currentAction: String = "",
        var playerList: MutableList<String> = mutableListOf(),
        var redHat: String = "",
        var blueHat: String = "",
        var goldHat: String = "",
        var blackHat: String = "",
        var greenHat: String = "",
        var pinkHat: String = "",
        var helperGirl : String = "",
        var helperMichelin : String = "",
        var helperButler : String = "",
        var helperZac : String = "",
        var helperBaby : String = "",
        var helperDragon : String = "",
        var queue: MutableList<String> = mutableListOf(),
        ){
//    val liveHasStarted = MutableLiveData<Boolean>(false)
//    val liveHatRed = MutableLiveData<String>() ; val liveHatBlue = MutableLiveData<String>(); val liveHatGold = MutableLiveData<String>()
//    val liveHatBlack = MutableLiveData<String>(); val liveHatPink = MutableLiveData<String>(); val liveHatGreen = MutableLiveData<String>()
//    val live = MutableLiveData<String>(); val live = MutableLiveData<String>(); val live = MutableLiveData<String>()
//    val live = MutableLiveData<String>(); val live = MutableLiveData<String>(); val live = MutableLiveData<String>()
    val liveTrigger = MutableLiveData<Unit>()
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance()}
    lateinit var gameDocRef : DocumentReference

    fun create(func: (created: Boolean) -> Unit) {
        d.game.code = d.game.code ?: UtilFuncs.createGamecode()
        gameDocRef = firestoreInstance.document("games/${d.game.code}")
        gameDocRef.get().addOnSuccessListener {
            if(!it.exists()) {
                d.game.host = d.fireUser?.uid!!
                gameDocRef.set(d.game)
                func(true)
            }
            else {
                func(true)
            }
        }
    }

    fun join(gameExists: (Boolean) -> Unit) {
        gameDocRef = firestoreInstance.document("games/${d.game.code}")
        gameDocRef.get().addOnSuccessListener {
            if(!it.exists()) {
                d.game.host = d.fireUser?.uid!!
                gameExists(false)
            }
            else {
                gameExists(true)
            }
        }
    }

    fun pickHat(hat:Hat, hatChanged: (hatChanged : Boolean) -> Unit) {
        gameDocRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.get(hat.type) == "") gameDocRef.update(hat.type, d.fireUser?.uid)
            if(d.player.hatClass != null) gameDocRef.update(d.player.hatClass!!.type, "")
            hatChanged(true)
        }
    }

    fun pickHelper(helper: Helper, helperChanged: (helperChanged : Boolean) -> Unit) {
        gameDocRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.get(helper.type) == "") gameDocRef.update(helper.type, d.fireUser?.uid)
            if(d.player.helperClass != null) gameDocRef.update(d.player.helperClass!!.type, "")
            helperChanged(true)
        }
    }

    fun startGame() {

    }

    fun getBasicGameInfo(receiveCode: (code: String) -> Unit) {
        gameDocRef.get().addOnSuccessListener { snapshot ->
            receiveCode(snapshot.get("code") as String)
        }
    }

    fun hatListenToGame(hat: Hat) {
        gameDocRef.addSnapshotListener { snapshot, error ->
            if(snapshot != null) {
                hat.owner.value = snapshot.get(hat.type) as String
            }
        }
    }

    fun helperListenToGame(helper:Helper) {
        gameDocRef.addSnapshotListener { snapshot, error ->
            if(snapshot != null) {
                helper.owner.value = snapshot.get(helper.type) as String
            }
        }
    }

}