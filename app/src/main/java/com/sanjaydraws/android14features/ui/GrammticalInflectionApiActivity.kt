package com.sanjaydraws.android14features.ui

import android.app.GrammaticalInflectionManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sanjaydraws.android14features.MainActivity
import com.sanjaydraws.android14features.databinding.ActivityGrammticalInflectionApiBinding
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
class GrammticalInflectionApiActivity : AppCompatActivity() {
    private var binding:ActivityGrammticalInflectionApiBinding? = null

    val grammaticalInflectionManager  by lazy {
        getSystemService(GRAMMATICAL_INFLECTION_SERVICE)
                as GrammaticalInflectionManager
    }

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, GrammticalInflectionApiActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrammticalInflectionApiBinding.inflate(layoutInflater)
        binding?.apply {
            setContentView(root)
            binding?.lifecycleOwner = this@GrammticalInflectionApiActivity
        }
        Log.d("TAGGGG", "onCreate: ")
        Log.d("TAGGGG", "Current Gender: ${grammaticalInflectionManager.applicationGrammaticalGender}")
        setAppLocale("fr")
        binding?.apply {
            btnFaminine.setOnClickListener{
                setGrammaticalGender(Configuration.GRAMMATICAL_GENDER_FEMININE)
            }
            btnNueter.setOnClickListener{
                setGrammaticalGender(Configuration.GRAMMATICAL_GENDER_NEUTRAL)

            }
            btnMasculine.setOnClickListener {
                setGrammaticalGender(Configuration.GRAMMATICAL_GENDER_MASCULINE)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAGGGG", "onRestart: ")
    }

    fun setGrammaticalGender(grammaticalGender:Int){
        grammaticalInflectionManager.setRequestedApplicationGrammaticalGender(
            grammaticalGender)
    }

    private fun setAppLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        val context = baseContext
        updateResources(configuration, context)
    }

    private fun updateResources(configuration: Configuration, context: Context) {
        context.createConfigurationContext(configuration)
    }
}