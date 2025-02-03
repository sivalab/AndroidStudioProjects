package com.example.mycontactlist

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity(), SaveDateListener {
    private lateinit var currentContact: Contact

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
        initTextChangedEvents()
        initSaveButton()
        setForEditing(false)
        currentContact = Contact()

    }

    private fun initTextChangedEvents() {

        // Bellow code for Capture Name
        val etContactName: EditText = findViewById(R.id.editName)
        etContactName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.contactName = etContactName.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture street Address
        val etStreetAddress: EditText = findViewById(R.id.editAddress)
        etStreetAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.streetAddress = etStreetAddress.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture City
        val etCity: EditText = findViewById(R.id.editCity)
        etCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.city = etCity.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture State
        val etState: EditText = findViewById(R.id.editState)
        etState.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.state = etState.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture Zipcode
        val etZipCode: EditText = findViewById(R.id.editZipcode)
        etZipCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.zipCode = etZipCode.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture Phone Number
        val etPhoneNumber: EditText = findViewById(R.id.editHome)

        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.phoneNumber = etPhoneNumber.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture Cell Number
        val etCellNumber: EditText = findViewById(R.id.editCell)
        etCellNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.cellNumber = etCellNumber.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })

        // Bellow code for Capture Email
        val etemail: EditText = findViewById(R.id.editEmail)
        etemail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                currentContact.email = etemail.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed
            }
        })
    }

    private fun initSaveButton() {
        val saveButton: Button = findViewById(R.id.buttonSave)

        saveButton.setOnClickListener {
            var wasSuccessful: Boolean
            val ds = ContactDataSource(this@MainActivity)

            try {
                ds.open()
                wasSuccessful = if (currentContact.contactID == -1) {
               val inserted = ds.insertContact(currentContact)
                    if (inserted) {
                        val newID = ds.getLastContactID()
                        newID.let { currentContact.contactID = it       }
                    }
                    inserted
                } else {
                    ds.updateContact(currentContact)
                }
                ds.close()
            } catch (e: Exception) {
                wasSuccessful = false
            }

            if (wasSuccessful) {
                val editToggle: ToggleButton = findViewById(R.id.toggleButtonEdit)
                editToggle.toggle()
                setForEditing(false)
            }

        }
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

    override fun didFinishDatePickerDialog(selectedTime: Calendar) {
        //
        val birthDay: Button = findViewById(R.id.btnBirthday)
        birthDay.text = DateFormat.format("MM/dd/yyyy", selectedTime).toString()
        currentContact.birthday = selectedTime
    }

}


