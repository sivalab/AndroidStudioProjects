package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        private void initTip15perButton () {
            Button displayButton = findview
        }
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

@Preview(showBackground = true, name = "background_02.png", fontScale = 0.85f)
@Composable
fun GreetingPreview() {
    TipCalcTheme {
        Greeting("Android")

    }
}
