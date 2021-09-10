package com.jetpack.circularprogressbar.component

import android.util.Size
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.circularprogressbar.ui.theme.Purple500

@Composable
fun DecreaseCircularProgressBar(
    percentage: Float,
    fontSize: TextUnit = 25.sp,
    radius: Dp = 100.dp,
    strokeWidth: Dp = 10.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed = remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed.value) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(
        key1 = true
    ) {
        animationPlayed.value = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
        ) {
            drawCircle(
                SolidColor(Color.LightGray),
                radius = size.width / 2,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val convertValue = (curPercentage.value / 30) * 360

            drawArc(
                brush = Brush.sweepGradient(
                    listOf(
                        Color(0xFFED9121),
                        Color(0xFF8DB600),
                        Color(0xFFD3212D)
                    )
                ),
                startAngle = -90f,
                sweepAngle = convertValue,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (curPercentage.value).toInt().toString(),
            color = Purple500,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}



















