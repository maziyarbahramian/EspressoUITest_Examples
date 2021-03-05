package com.mazibahrami.espresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.mazibahrami.espresso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "AppDebug"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLaunchDialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        MaterialDialog(this)
            .show {
                input(
                    waitForPositiveButton = true,
                    allowEmpty = false
                ) { dialog, name ->
                    setNameToTextView(name.toString())
                    showToast(buildToastMessage(name.toString()))
                }
                title(R.string.text_enter_name)
                positiveButton(R.string.text_ok)
            }
    }

    private fun setNameToTextView(name: String) {
        binding.textName.text = name
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun buildToastMessage(name: String): String {
            return "Your name is $name"
        }
    }
}