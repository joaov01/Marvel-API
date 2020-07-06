package com.example.marvelcharactresapplication.general

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.marvelcharactresapplication.R

class MarvelLoading {

    private fun create(activity: Activity): Dialog{
        val dialog = Dialog(activity, R.style.Marvel_Loading)
        dialog.setOwnerActivity(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_layout)
        dialog.setCancelable(false)
        val window = dialog.window
        if(window != null){
            window.attributes.windowAnimations = R.style.Marvel_Loading
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.window!!.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
            }

        }
        return dialog
    }


    fun show(activity: Activity): Dialog{
        val dialog = create(activity)
        dialog.show()
        return dialog
    }
}