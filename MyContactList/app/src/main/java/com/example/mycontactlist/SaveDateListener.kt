package com.example.mycontactlist

import java.util.Calendar

interface SaveDateListener {
    fun didFinishDatePickerDialog(selectedTime: Calendar)
}