package com.example.mycontactlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Set padding for system insets
        val rootView = findViewById<View>(R.id.mainLayout)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListButton()
        initMapButton()
        initSettingsButton()
        initToggleButton()
        setForEditing(false)

    }

    private fun initListButton() {
        val ibList: ImageButton = findViewById(R.id.imageButtonList)
        ibList.setOnClickListener {
            val intent = Intent(this, ContactListActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    private fun initMapButton() {
        val ibList: ImageButton = findViewById(R.id.imageButtonMap)
        ibList.setOnClickListener {
            val intent = Intent(this, ContactMapActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    private fun initSettingsButton() {
        val ibList: ImageButton = findViewById(R.id.imageButtonSettings)
        ibList.setOnClickListener {
            val intent = Intent(this, ContactSettingsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    private fun initToggleButton() {
        val editToggle: ToggleButton = findViewById(R.id.toggleButtonEdit)
        editToggle.setOnClickListener {
            setForEditing(editToggle.isChecked)
        }
    }

    private fun setForEditing(enabled: Boolean) {
        val editName: EditText = findViewById(R.id.editName)
        val editAddress: EditText = findViewById(R.id.editAddress)
        val editCity: EditText = findViewById(R.id.editCity)
        val editState: EditText = findViewById(R.id.editState)
        val editZipCode: EditText = findViewById(R.id.editZipcode)
        val editPhone: EditText = findViewById(R.id.editHome)
        val editCell: EditText = findViewById(R.id.editCell)
        val editEmail: EditText = findViewById(R.id.editEmail)
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonChange: Button = findViewById(R.id.btnBirthday)

        editName.isEnabled = enabled
        editAddress.isEnabled = enabled
        editCity.isEnabled = enabled
        editState.isEnabled = enabled
        editZipCode.isEnabled = enabled
        editCell.isEnabled = enabled
        editEmail.isEnabled = enabled
        editPhone.isEnabled = enabled
        buttonSave.isEnabled = enabled
        buttonChange.isEnabled = enabled

        if (enabled) {
            editName.requestFocus()
        }
    }


}

