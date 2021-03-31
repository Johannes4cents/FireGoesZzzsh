package firegoeszzzsh.icegoesbrrr.firebase

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs.createGamecode

object FireUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance()}
    private val userDocRef: DocumentReference
        get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid ?: throw java.lang.NullPointerException("UID is null")}")


    fun initFireUserIfFirstTime(onComplete: () -> Unit) {
        userDocRef.get().addOnSuccessListener {  documentSnaptshot ->
            if(!documentSnaptshot.exists()) {
                val newUser = FireUser()
            }
        }
    }


}

data class FireUser (
        val nickName: String = "",
        val hat: String = "",
        val helper: String = "",
        var host: Boolean = false,
        var gamesPlayed: Int = 0
) {
}



