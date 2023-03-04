package com.example.jetbmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.jetbmicalculator.ui.theme.JetBMICalculatorTheme
import com.example.jetbmicalculator.viewModels.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel) {
    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.Start) {
        Text(
            text = "BMI計算アプリ",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(30.dp))

        //身長
        PinkLabeledTextField(
            value = viewModel.height,
            onValueChange = { height ->
                viewModel.height = height
            },
            label = "身長(cm)",
            placeholder = "170"
        )

        Spacer(modifier = Modifier.height(20.dp))

        //体重
        PinkLabeledTextField(value = viewModel.weight,
            onValueChange = { weight ->
                viewModel.weight = weight
            },
            label = "体重(kg)",
            placeholder = "60"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfff85f6a)),
            onClick = {
                viewModel.calculateBMI()
            }) {
            Text(text = "計算する", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        //結果表示
        Text(
            text = "あなたのBMIは${viewModel.bmi}です",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun PinkLabeledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    Column {
        //身長
        Text(text = label, color = Color(0xfff85f6a), fontWeight = FontWeight.Bold)
        TextField(
            //Color.Transparent → 透明
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) }
        )
    }
}

@Preview(widthDp = 320, showBackground = true)
@Composable
fun PreviewMain() {
    JetBMICalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        }
    }
}