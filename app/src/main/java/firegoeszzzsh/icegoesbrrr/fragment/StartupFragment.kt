package firegoeszzzsh.icegoesbrrr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.StartupBinding

import android.util.Log


class StartupFragment: Fragment() {

    companion object {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = StartupBinding.inflate(inflater)
        return b.root
    }

    override fun onStart() {
        super.onStart()
        Log.i("testen", "onstart bla")
        d.fireUser = d.mAuth.currentUser
        updateUI(d.fireUser)
    }

    fun updateUI(user: FirebaseUser?) {
        if(user == null) {
            findNavController().navigate(R.id.action_startupFragment_to_signUpFragment)
        } else {
            findNavController().navigate(R.id.action_startupFragment_to_startScreenFragment)
        }
    }

}





