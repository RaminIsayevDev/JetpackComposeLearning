package com.example.canvasdrawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalTime

@Composable
fun DigitalClock(
    modifier: Modifier = Modifier.fillMaxSize(),
    color: Color = Color(0xFF00FF55),
    hour: Int,
    minute: Int,
    amPm: String
) {




    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Используем веса (weight) вместо фиксированных размеров, чтобы часы масштабировались
        // Пропорции: Цифра (100), Пробел (10/20), Двоеточие (20)

        SevenSegmentDigit(hour / 10, color, Modifier.weight(10f).aspectRatio(100f/180f))
        Spacer(Modifier.weight(1f))

        SevenSegmentDigit(hour % 10, color, Modifier.weight(10f).aspectRatio(100f/180f))
        Spacer(Modifier.weight(2f))

        Colon(color, Modifier.weight(2f).aspectRatio(20f/120f))
        Spacer(Modifier.weight(2f))

        SevenSegmentDigit(minute / 10, color, Modifier.weight(10f).aspectRatio(100f/180f))
        Spacer(Modifier.weight(1f))

        SevenSegmentDigit(minute % 10, color, Modifier.weight(10f).aspectRatio(100f/180f))

        Spacer(Modifier.weight(2f))
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

@Preview(showSystemUi = true)
@Composable
fun ClockPreview() {
    DigitalClock(hour = 10, minute = 15, amPm = "PM", modifier = Modifier.padding(16.dp))
}