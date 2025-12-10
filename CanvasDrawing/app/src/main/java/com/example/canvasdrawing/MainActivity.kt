package com.example.canvasdrawing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.LocalTime
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val time = remember { mutableStateOf(LocalTime.now()) }
            val is24h: Boolean = false
            val amPm = if (time.value.hour < 12) "AM" else "PM"

            // Обновление каждую секунду
            LaunchedEffect(Unit) {
                while (true) {
                    time.value = LocalTime.now()
                    delay(1000)
                }
            }

            val hour = if (is24h) time.value.hour else time.value.hour % 12
            val minute = time.value.minute
            DigitalClock(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                color = Color(0xFFC90F00),
                hour,
                minute,
                amPm
            )
        }
    }
}