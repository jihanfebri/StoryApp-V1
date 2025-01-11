package com.example.story.app.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.story.app.R
import com.example.story.app.databinding.ActivitySettingBinding
import com.example.story.app.util.Message
import com.example.story.app.util.PrefsManager

class SettingActivity : AppCompatActivity() {

    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    private lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefsManager = PrefsManager(this)

        binding.btnChangeLang.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        binding.iconBack.setOnClickListener{
            finish()
        }

        binding.btnLogout.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(resources.getString(R.string.logOut))
            dialog.setMessage(getString(R.string.makeSure))
            dialog.setPositiveButton(getString(R.string.yesLogout)) {_,_ ->
                prefsManager.clear()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                Message.setMessage(this, getString(R.string.warningLogout))
            }
            dialog.setNegativeButton(getString(R.string.noLogout)) {_,_ ->
                Message.setMessage(this, getString(R.string.noSure))
            }
            dialog.show()
        }
    }
}