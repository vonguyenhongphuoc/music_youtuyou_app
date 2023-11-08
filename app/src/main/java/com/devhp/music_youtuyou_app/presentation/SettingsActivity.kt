package com.devhp.music_youtuyou_app.presentation

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.devhp.music_youtuyou_app.R
import com.devhp.music_youtuyou_app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onStart() {
        super.onStart()
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 500)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var wifiManager: WifiManager? = null
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnWifi.setOnClickListener {
                
                val fragment = SettingsDialogFragment()
                fragment.show(supportFragmentManager, "SettingsDialogFragment")
            }
            btnBluetooth.setOnClickListener {
                val panelIntent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
                startActivityForResult(panelIntent, 1)
            }
            btnNetworkMobile.setOnClickListener {
//                val panelIntent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
//                startActivityForResult(panelIntent, 1)

                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.data = Uri.fromParts("package", packageName, null)
                startActivityForResult(intent, 1)
            }
        }
    }
}

class SettingsDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Open Wi-Fi Settings?")
        builder.setPositiveButton("Open") { _, _ ->
            startActivity(intent)
        }
        builder.setNegativeButton("Cancel") { _, _ ->
            // Handle cancel button click
        }

        // Thiết lập kích thước dialog
        val dialog = builder.create()
        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(window?.attributes)
        layoutParams.width =
            resources.getDimensionPixelSize(R.dimen.dialog_width) // Thay đổi kích thước tại đây
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = layoutParams

        return dialog
    }
}