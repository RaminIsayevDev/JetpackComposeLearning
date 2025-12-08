package com.example.canvasdrawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalTime

@Composable
fun DigitalClock(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF00FF55),
    is24h: Boolean = false
) {
    val time = remember { mutableStateOf(LocalTime.now()) }

    // Обновление каждую секунду
    LaunchedEffect(Unit) {
        while (true) {
            time.value = LocalTime.now()
            delay(1000)
        }
    }

    val hour = if (is24h) time.value.hour else time.value.hour % 12
    val minute = time.value.minute

    val amPm = if (time.value.hour < 12) "AM" else "PM"

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SevenSegmentDigit(hour / 10, color, Modifier.size(100.dp, 180.dp))
        Spacer(Modifier.width(10.dp))

        SevenSegmentDigit(hour % 10, color, Modifier.size(100.dp, 180.dp))
        Spacer(Modifier.width(20.dp))

        Colon(color, Modifier.size(20.dp, 120.dp))
        Spacer(Modifier.width(20.dp))

        SevenSegmentDigit(minute / 10, color, Modifier.size(100.dp, 180.dp))
        Spacer(Modifier.width(10.dp))

        SevenSegmentDigit(minute % 10, color, Modifier.size(100.dp, 180.dp))

        Spacer(Modifier.width(20.dp))
        AmPmLabel(amPm, color)
    }

}

@Composable
fun Colon(
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier) {
        val r = size.minDimension * 0.15f

        drawCircle(color, radius = r, center = Offset(size.width / 2, size.height * 0.30f))
        drawCircle(color, radius = r, center = Offset(size.width / 2, size.height * 0.70f))
    }
}

@Composable
fun AmPmLabel(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(start = 8.dp)
    )
}
