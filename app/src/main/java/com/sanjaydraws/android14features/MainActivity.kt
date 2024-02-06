package com.sanjaydraws.android14features

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sanjaydraws.android14features.databinding.ActivityMainBinding
import com.sanjaydraws.android14features.ui.GrammticalInflectionApiActivity
import com.sanjaydraws.android14features.ui.ScreenShotDetectionActivity
import com.sanjaydraws.android14features.ui.ShareSheetActivity
import com.sanjaydraws.android14features.utils.SnackBarUtils

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding ?=null
    companion object {
        const val TAG = "MainActivity"
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.apply {
            setContentView(root)
            binding?.lifecycleOwner = this@MainActivity
        }
        binding?.btnScreenShotDetectionApi?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                ScreenShotDetectionActivity.start(this)
            } else {
                binding?.root?.let { SnackBarUtils.showSnackBar(it,"Screenshot detection Api is not supported below Android 14",3000) }
            }
        }
        binding?.btnShareSheet?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                ShareSheetActivity.start(this)
            } else {
                binding?.root?.let { SnackBarUtils.showSnackBar(it,"ShareSheet custom Action is not supported below Android 14",3000) }
            }
        }
        binding?.btnGrammaticalInflectionApi?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                GrammticalInflectionApiActivity.start(this)
            } else {
                binding?.root?.let { SnackBarUtils.showSnackBar(it,"Grammtical " +
                        "Inflection Api is not supported below Android 14",3000) }
            }
        }
    }
}