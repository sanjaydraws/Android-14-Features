package com.sanjaydraws.android14features.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sanjaydraws.android14features.databinding.ActivityScreenShotDetectionBinding
import com.sanjaydraws.android14features.utils.SnackBarUtils


// this activity only works for Android 14 or above
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
class ScreenShotDetectionActivity : AppCompatActivity(), Activity.ScreenCaptureCallback{
    private var binding: ActivityScreenShotDetectionBinding?=null
    companion object {
        const val TAG = "ScreenShotDetectionActivityTAG"
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ScreenShotDetectionActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenShotDetectionBinding.inflate(layoutInflater)
        binding?.apply {
            setContentView(root)
            binding?.lifecycleOwner = this@ScreenShotDetectionActivity
        }
        binding?.detectScreenshot?.setOnClickListener {
        }
    }

    override fun onStart() {
        super.onStart()
        // Register callback to detect screenshot
        registerScreenCaptureCallback(mainExecutor, this)
    }

    override fun onStop() {
        super.onStop()
        // unregister callback to detect screenshot
        unregisterScreenCaptureCallback(this)
    }


    override fun onScreenCaptured() {
        binding?.root?.let { SnackBarUtils.showSnackBar(it,"ScreenShot detected",6000) }
        Log.d(TAG, "onScreenCaptured: Screenshot detected")
    }



}