package com.sanjaydraws.android14features.ui

import android.app.PendingIntent
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.chooser.ChooserAction
import androidx.annotation.RequiresApi
import com.sanjaydraws.android14features.databinding.ActivityShareSheetBinding

class ShareSheetActivity : AppCompatActivity() {
    private var binding: ActivityShareSheetBinding?=null

    companion object {
        const val TAG = "ShareSheetActivityTAG"
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ShareSheetActivity::class.java)
            context.startActivity(starter)
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareSheetBinding.inflate(layoutInflater)
        binding?.apply {
            setContentView(root)
            binding?.lifecycleOwner = this@ShareSheetActivity
        }
        binding?.btnSimpleShare?.setOnClickListener {
            shareText("Hello this is string for sharing")
        }
        binding?.btnShareSheet?.setOnClickListener {
            share("This is sample text to share")
        }
    }

    private fun shareText(text: String?) {
        val sendIntent = Intent()
        sendIntent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, title)
        startActivity(shareIntent)
    }


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun share(textToShare:String){
        // creating custom Intent that we want to launch
        val customIntent1 =  Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, "Custom Actions in Sharesheet.")
        }
        val customIntent2 = Intent(applicationContext, DemoActivity::class.java)

        // creating pending intent for an activity
        val pendingIntent1 = PendingIntent.getActivity(
            applicationContext,
            0,
            customIntent1,
            PendingIntent.FLAG_IMMUTABLE
        )
        val pendingIntent2 = PendingIntent.getActivity(
            applicationContext,
            0,
            customIntent2,
            PendingIntent.FLAG_IMMUTABLE
        )

        // define action with Icon, Label, Pending Intent
        val customAction1 = ChooserAction.Builder(
            // Icon
            Icon.createWithResource(baseContext, com.google.android.material.R.drawable.material_ic_edit_black_24dp),
            // Label
            "First Custom Action",
            // PendingIntent
            pendingIntent1
        ).build()

        val customAction2 = ChooserAction.Builder(
            // Icon
            Icon.createWithResource(
                baseContext,
                com.google.android.material.R.drawable.material_ic_keyboard_arrow_previous_black_24dp
            ),
            // Label
            "Custom Action",
            // PendingIntent
            pendingIntent2
        ).build()

        //creating intent
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }

        // Show the shareSheet with custom actions
        startActivity(Intent.createChooser(intent, "Share using...").apply {
            // We need to pass an array
            putExtra(Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS, arrayOf(customAction1,customAction2))
        })
    }

}