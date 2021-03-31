package firegoeszzzsh.icegoesbrrr.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.databinding.SignUpBinding

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import firegoeszzzsh.icegoesbrrr.R


class SignUpFragment: Fragment() {
    lateinit var b : SignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = SignUpBinding.inflate(inflater)
        clickListener()

        return b.root
    }

    private fun clickListener() {
        b.createAccountButton.setOnClickListener {
            if(b.emailInput.length() > 5 && b.passwordInput.length() > 5) createAccount(b.emailInput.text.toString(), b.passwordInput.text.toString())
        }

        b.signInButton.setOnClickListener {
            if(b.emailInput.length() > 5 && b.passwordInput.length() > 5) signInWithEmail(b.emailInput.text.toString(), b.passwordInput.text.toString())
        }

        b.googleSignInButton.setOnClickListener {
            googleSignIn()
        }
    }

    private fun createAccount(email: String, pw: String) {
        d.mAuth.createUserWithEmailAndPassword(email, pw)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user: FirebaseUser? = d.mAuth.currentUser
                    findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@SignUpFragment.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                }
            }
    }
    private fun signInWithEmail(email: String, pw: String) {
        d.mAuth.signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener(requireActivity(),
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        d.fireUser = d.mAuth.currentUser
                        findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@SignUpFragment.context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                    }

                    // ...
                })
    }

    fun googleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, 1337
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1337) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java)
            firebaseAuthWithGoogle(account?.idToken!!)
        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        d.mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    d.fireUser = d.mAuth.currentUser
                    findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    findNavController().navigate(R.id.action_signUpFragment_to_startupFragment)
                }
            }
    }
}

