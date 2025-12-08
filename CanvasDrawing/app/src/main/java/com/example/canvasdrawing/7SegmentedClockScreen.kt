package com.example.canvasdrawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp

@Composable
fun SevenSegmentDigit(
    digit: Int,
    color: Color = Color(0xFF00FF66),
    modifier: Modifier = Modifier
        .size(100.dp, 180.dp)
) {
    Canvas(modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val t = w * 0.12f // thickness

        fun segOn(show: Boolean, path: Path) {
            if (show) {
                drawPath(path, color)
                // glow
                drawPath(path, color.copy(alpha = 0.4f), style = Fill, blendMode = BlendMode.Screen)
            }
        }

        // Define segment shapes
        val top = Path().apply {
            moveTo(t, 0f); lineTo(w - t, 0f); lineTo(w - 2*t, t); lineTo(2*t, t); close()
        }
        val upperLeft = Path().apply {
            moveTo(0f, t); lineTo(t, 2*t); lineTo(t, h/2 - t); lineTo(0f, h/2 - 2*t); close()
        }
        val upperRight = Path().apply {
            moveTo(w, t); lineTo(w - t, 2*t); lineTo(w - t, h/2 - t); lineTo(w, h/2 - 2*t); close()
        }
        val middle = Path().apply {
            moveTo(t, h/2 - t); lineTo(w - t, h/2 - t); lineTo(w - 2*t, h/2 + t); lineTo(2*t, h/2 + t); close()
        }
        val lowerLeft = Path().apply {
            moveTo(0f, h/2 + t); lineTo(t, h/2 + 2*t); lineTo(t, h - 2*t); lineTo(0f, h - t); close()
        }
        val lowerRight = Path().apply {
            moveTo(w, h/2 + t); lineTo(w - t, h/2 + 2*t); lineTo(w - t, h - 2*t); lineTo(w, h - t); close()
        }
        val bottom = Path().apply {
            moveTo(t, h - t); lineTo(w - t, h - t); lineTo(w - 2*t, h); lineTo(2*t, h); close()
        }

        val segments = mapOf(
            0 to listOf(true, true, true, false, true, true, true),
            1 to listOf(false, false, true, false, false, true, false),
            2 to listOf(true, false, true, true, true, false, true),
            3 to listOf(true, false, true, true, false, true, true),
            4 to listOf(false, true, true, true, false, true, false),
            5 to listOf(true, true, false, true, false, true, true),
            6 to listOf(true, true, false, true, true, true, true),
            7 to listOf(true, false, true, false, false, true, false),
            8 to listOf(true, true, true, true, true, true, true),
            9 to listOf(true, true, true, true, false, true, true),
        )

        val seg = segments[digit]!!

        segOn(seg[0], top)
        segOn(seg[1], upperLeft)
        segOn(seg[2], upperRight)
        segOn(seg[3], middle)
        segOn(seg[4], lowerLeft)
        segOn(seg[5], lowerRight)
        segOn(seg[6], bottom)
    }
}
