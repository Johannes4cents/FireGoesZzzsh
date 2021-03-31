package firegoeszzzsh.icegoesbrrr.classes

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import firegoeszzzsh.icegoesbrrr.R
import firegoeszzzsh.icegoesbrrr.d
import firegoeszzzsh.icegoesbrrr.utility.UtilFuncs

class AppButton(context: Context, attrs: AttributeSet): androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    var type: String

    init {
        context.obtainStyledAttributes(attrs, R.styleable.AppButton).apply {
            try{
                type = getString(R.styleable.AppButton_btnType).toString()
            } finally {
                recycle()
            }
        }
        setClickListeners()
    }



    private fun setClickListeners() {
        setOnClickListener {
            when(type) {
                "hostGame" -> {}
                "joinGame" -> {
                    findNavController().navigate(R.id.action_startScreenFragment_to_gameFragment)
                }
                "1phoneGame" -> {}
            }
        }
    }
}