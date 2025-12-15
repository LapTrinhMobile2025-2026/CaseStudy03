package com.example.casestudy03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorScreen()
        }
    }
}

@Composable
fun CalculatorScreen() {
    // 1. Khai báo State để lưu dữ liệu
    var textA by remember { mutableStateOf("") }
    var textB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Hàm xử lý tính toán chung
    fun calculate(operator: String) {
        val a = textA.toDoubleOrNull()
        val b = textB.toDoubleOrNull()

        if(a == null && b == null){
            result = "Vui lòng nhập số A và số B"
            return
        } else if (a == null) {
            result = "Vui lòng nhập số A"
            return
        }else if(b==null){
            result = "Vui lòng nhập số B"
            return
        }

        val res = when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) {a / b
            }else{
                result = "Không thể chia cho 0"
                return
            }
            else -> 0.0
        }

        // Làm tròn kết quả
        result = if (res % 1.0 == 0.0) res.toInt().toString() else res.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 03",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        // Ô nhập số A
        OutlinedTextField(
            value = textA,
            onValueChange = { textA = it },
            label = { Text("Nhập số A") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Hàng nút phép tính
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Nút Cộng
            Button(
                onClick = { calculate("+") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)),
                shape = RoundedCornerShape(10.dp)
            ) { Text("+", fontSize = 20.sp) }

            // Nút Trừ
            Button(
                onClick = { calculate("-") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
                shape = RoundedCornerShape(10.dp)
            ) { Text("-", fontSize = 20.sp) }

            // Nút Nhân
            Button(
                onClick = { calculate("*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)),
                shape = RoundedCornerShape(10.dp)
            ) { Text("*", fontSize = 20.sp) }

            // Nút Chia
            Button(
                onClick = { calculate("/") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF252424)),
                shape = RoundedCornerShape(10.dp)
            ) { Text("/", fontSize = 20.sp) }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Ô nhập số B
        OutlinedTextField(
            value = textB,
            onValueChange = { textB = it },
            label = { Text("Nhập số B") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Hiển thị kết quả
        Text(
            text = "Kết quả: $result",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}