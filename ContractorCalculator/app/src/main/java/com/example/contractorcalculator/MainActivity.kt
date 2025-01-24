package com.example.contractorcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //
        // Get References from Layout
        val laborCostEditText: EditText = findViewById(R.id.editTextLaborCost)
        val materialCostEditText: EditText = findViewById(R.id.editTextMaterialCost)
        val textViewAmtSubTotal: TextView = findViewById(R.id.textViewSubTotalAmount)
        val textViewAmtTax: TextView = findViewById(R.id.textViewTaxAmount)
        val textViewAmtTotal: TextView = findViewById(R.id.textViewTotalAmount)
        
        val calculateButton: Button = findViewById(R.id.buttonCalculate)
        

        // Get Listener for Button
        calculateButton.setOnClickListener {
            val laborCostText =  laborCostEditText.text.toString()
            val materialCostText =  materialCostEditText.text.toString()

            val laborCost: Double = if (laborCostText.isEmpty()) {

                0.00
            } else {
                laborCostText.toDouble()
            }
            val materialCost: Double = if (materialCostText.isEmpty()) {

                0.00
            } else {
                materialCostText.toDouble()
            }
            val subTotalAmt: Double = laborCost + materialCost
            val taxAmt: Double = subTotalAmt*0.05
            val totalAmt: Double = subTotalAmt + taxAmt

            textViewAmtSubTotal.text = getString(R.string.subTotalAmt_String, subTotalAmt)
            textViewAmtTax.text = getString(R.string.subTotalAmt_String, taxAmt)
            textViewAmtTotal.text = getString(R.string.subTotalAmt_String, totalAmt)
        }



    }
}

