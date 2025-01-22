package com.example.tipcalc

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalc.ui.theme.TipCalcTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val billAmountEditText: EditText = findViewById(R.id.ip_bill_amount)
        val otDisplayResultTextView: TextView = findViewById(R.id.result_Display)

        // get references to buttons
        val tip15button: Button = findViewById(R.id.button_click_per15)
        val tip18button: Button = findViewById(R.id.button_click_per18)
        val tip20button: Button = findViewById(R.id.button_click_per20)
        var result: String
        var tipPerVal: Int
        //  Set Click listeners for each button
        tip15button.setOnClickListener {
            tipPerVal = 15
            result = calculateTip(this,billAmountEditText, tipPerVal )
            otDisplayResultTextView.text = result
        }

        tip18button.setOnClickListener {
            tipPerVal = 18
            result = calculateTip(this,billAmountEditText, tipPerVal)
            otDisplayResultTextView.text = result
        }

        tip20button.setOnClickListener {
            tipPerVal = 20
            result = calculateTip(this,billAmountEditText, tipPerVal)
            otDisplayResultTextView.text = result
        }



//        enableEdgeToEdge()
//        setContent {
//            TipCalcTheme {
//               Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                   Greeting(
//                       name = "Android",
//                      modifier = Modifier.padding(innerPadding)
//                   )
//                }
//           }
//
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface (color = Color.Cyan)  {
        Text(
            text = "Hello My Name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}

private fun calculateTip(context: Context,
                         billAmountEditText: EditText,
                         tipPercentage: Int
): String {

    val billAmountText =  billAmountEditText.text.toString()
    //val tipPerVal: Int = tipPercentage

    if (billAmountText.isNotEmpty()) {
        val billAmount: Float = billAmountText.toFloat()
        val tipAmount: Float  = billAmount * (tipPercentage.toFloat() / 100)
        val totalAmount: Float = billAmount + tipAmount
        return context.getString(R.string.result_Display, tipAmount, totalAmount)

    }
    else{
        return "Please Enter Valid Amount"
    }
}




@Preview(showBackground = true, name = "background_02.png", fontScale = 0.85f)
@Composable
fun GreetingPreview() {
    TipCalcTheme {
        Greeting("Android")

    }
}
