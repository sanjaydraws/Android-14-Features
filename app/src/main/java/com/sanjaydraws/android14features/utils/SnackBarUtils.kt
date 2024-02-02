package com.sanjaydraws.android14features.utils

import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackBarUtils {

    fun showSnackBar(view: View, message: String, duration: Int) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

        // Set the duration of the SnackBar
        setSnackBarDuration(snackBar, duration)
        snackBar.show()
    }

    private fun setSnackBarDuration(snackBar: Snackbar, duration: Int) {
        // Use a Handler to dismiss the SnackBar after the specified duration
        val handler = Handler()
        handler.postDelayed({ snackBar.dismiss() }, duration.toLong())
    }
}