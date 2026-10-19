package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark

@Composable
fun JizanPalmTreeLogo(
    modifier: Modifier = Modifier,
    tint: Color = JizanBrandRed
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Palm Trunk
        val trunkPath = Path().apply {
            moveTo(w * 0.44f, h * 0.95f)
            lineTo(w * 0.56f, h * 0.95f)
            lineTo(w * 0.53f, h * 0.45f)
            lineTo(w * 0.47f, h * 0.45f)
            close()
        }
        drawPath(trunkPath, color = tint, style = Fill)

        // Trunk ridges
        for (i in 1..4) {
            val y = h * (0.50f + i * 0.09f)
            drawLine(
                color = Color.White.copy(alpha = 0.8f),
                start = Offset(w * 0.46f, y),
                end = Offset(w * 0.54f, y),
                strokeWidth = 2.dp.toPx()
            )
        }

        // Center frond
        val centerFrond = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.50f, h * 0.20f, w * 0.50f, h * 0.08f)
            quadraticTo(w * 0.50f, h * 0.25f, w * 0.50f, h * 0.45f)
        }
        drawPath(centerFrond, color = tint, style = Stroke(width = 3.dp.toPx()))

        // Left fronds
        val frondL1 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.35f, h * 0.25f, w * 0.15f, h * 0.28f)
        }
        drawPath(frondL1, color = tint, style = Stroke(width = 3.dp.toPx()))

        val frondL2 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.30f, h * 0.35f, w * 0.12f, h * 0.45f)
        }
        drawPath(frondL2, color = tint, style = Stroke(width = 3.dp.toPx()))

        val frondL3 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.35f, h * 0.45f, w * 0.20f, h * 0.60f)
        }
        drawPath(frondL3, color = tint, style = Stroke(width = 2.5.dp.toPx()))

        // Right fronds
        val frondR1 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.65f, h * 0.25f, w * 0.85f, h * 0.28f)
        }
        drawPath(frondR1, color = tint, style = Stroke(width = 3.dp.toPx()))

        val frondR2 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.70f, h * 0.35f, w * 0.88f, h * 0.45f)
        }
        drawPath(frondR2, color = tint, style = Stroke(width = 3.dp.toPx()))

        val frondR3 = Path().apply {
            moveTo(w * 0.50f, h * 0.45f)
            quadraticTo(w * 0.65f, h * 0.45f, w * 0.80f, h * 0.60f)
        }
        drawPath(frondR3, color = tint, style = Stroke(width = 2.5.dp.toPx()))
    }
}

@Composable
fun JizanOfficialLogoBadge(
    modifier: Modifier = Modifier,
    isDarkBackground: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        JizanPalmTreeLogo(
            modifier = Modifier.size(32.dp),
            tint = JizanBrandRed
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "جيزان",
                    color = JizanBrandRed,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "JIZAN",
                    color = if (isDarkBackground) Color.White else JizanNavyDark,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp
                )
            }
            Text(
                text = "مصنع جيزان للصناعات البلاستيكية",
                color = if (isDarkBackground) Color(0xFF94A3B8) else Color(0xFF64748B),
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
