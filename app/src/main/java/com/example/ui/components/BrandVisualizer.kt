package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

@Composable
fun BrandVisualizer(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: المواسير, 1: التوصيلات

    // Pipe customization states
    var selectedPipeSize by remember { mutableStateOf("Ø110mm") }
    var selectedPipePressure by remember { mutableStateOf("PN10") }
    var selectedPipeStandard by remember { mutableStateOf("ASTM D1785") }

    // Fitting customization states
    var selectedFittingType by remember { mutableStateOf("كوع 90°") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("brand_visualizer_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        JizanPalmTreeLogo(modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "محاكي الهوية البصرية الرسمية",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = JizanNavyPrimary
                        )
                    }
                    Text(
                        text = "الشعار المطبوع + الخط الأزرق الطولي على المواسير والتوصيلات",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEFF6FF))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (selectedTab == 0) "JIZAN PIPES" else "JIZAN FITTINGS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTab == 0) JizanBrandRed else JizanElectricBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Selector Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color(0xFFF8FAFC),
                contentColor = JizanNavyPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(JizanBrandRed)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "المواسير: شعار + خط أزرق طولي",
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }
                    },
                    modifier = Modifier.testTag("tab_pipes_identity")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(JizanElectricBlue)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "التوصيلات: خط أزرق + شعار جيزان",
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }
                    },
                    modifier = Modifier.testTag("tab_fittings_identity")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Content based on tab
            if (selectedTab == 0) {
                // Exact Pipe rendering from authentic photo
                OfficialPipeDisplay(
                    size = selectedPipeSize,
                    pressure = selectedPipePressure,
                    standard = selectedPipeStandard
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "تخصيص بيانات طباعة الماسورة:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF334155)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Pipe Sizes Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val sizes = listOf("Ø50mm", "Ø110mm", "Ø160mm", "Ø200mm")
                    sizes.forEach { s ->
                        FilterChip(
                            selected = selectedPipeSize == s,
                            onClick = { selectedPipeSize = s },
                            label = { Text(s, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = JizanNavyPrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                // Pressure Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val pressures = listOf("PN6", "PN10", "PN16")
                    pressures.forEach { p ->
                        FilterChip(
                            selected = selectedPipePressure == p,
                            onClick = { selectedPipePressure = p },
                            label = { Text(p, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = JizanBrandRed,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                // Standards Chips (featuring ASTM D1785 from user's image)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val standards = listOf("ASTM D1785", "ISO 1452", "DIN 8062")
                    standards.forEach { std ->
                        FilterChip(
                            selected = selectedPipeStandard == std,
                            onClick = { selectedPipeStandard = std },
                            label = { Text(std, fontSize = 11.sp) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Official Pipe Signature Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF1F2))
                        .border(1.dp, Color(0xFFFECDD3), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = JizanBrandRed,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "الهوية الرسمية المعتمدة للمواسير:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = JizanBrandRed
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. خط أزرق طولي متصل يمتد على كامل طول الماسورة.\n2. شعار النخلة وكلمة جيزان باللون الأحمر مع JIZAN بالأسود.\n3. بيانات القطر والضغط والمواصفة المعتمدة: UPVC PIPE $selectedPipeSize $selectedPipePressure $selectedPipeStandard",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF881337),
                            lineHeight = 17.sp
                        )
                    }
                }
            } else {
                // Fitting Visualizer
                OfficialFittingDisplay(
                    fittingType = selectedFittingType
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "اختر نوع التوصيلة للمعاينة:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF334155)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val fittings = listOf("كوع 90°", "تيه متساوي", "جلبة توصيل", "سدادة ملولبة")
                    fittings.forEach { fit ->
                        FilterChip(
                            selected = selectedFittingType == fit,
                            onClick = { selectedFittingType = fit },
                            label = { Text(fit, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = JizanElectricBlue,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Fitting Identity Summary Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEFF6FF))
                        .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = JizanElectricBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "الهوية الرسمية المعتمدة للتوصيلات:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = JizanElectricBlue
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "⚪ جسم توصيلة أبيض ناصع مصقول بالحقن عالي الكثافة.\n🔵 حزام الخط الأزرق المميز (دائري أو محيطي) لتمييز المنتج الأصلي.\n🔴 شعار نخلة جيزان الأحمر + JIZAN مطبوع مباشرة على كل توصيلة.",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1E40AF),
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OfficialPipeDisplay(
    size: String,
    pressure: String,
    standard: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F172A))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        // Engineering Pipe rendering matching user's image exactly
        Column(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .height(84.dp)
                .shadow(10.dp, RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFF8FAFC),
                            Color(0xFFE2E8F0),
                            Color(0xFFFFFFFF)
                        )
                    )
                )
                .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(6.dp)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // ⭐ 1. THE BLUE LONGITUDINAL STRIPE RUNNING ALONG THE ENTIRE PIPE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color(0xFF0077FF))
            )

            // ⭐ 2. THE OFFICIAL BRAND STAMP PRINTED ON PIPE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Official Red Palm + جيزان JIZAN
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    JizanPalmTreeLogo(
                        modifier = Modifier.size(24.dp),
                        tint = JizanBrandRed
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "جيزان",
                        color = JizanBrandRed,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "JIZAN",
                        color = Color(0xFF0F172A),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }

                // Printed Technical Specs
                Text(
                    text = "UPVC PIPE   $size $pressure $standard",
                    color = Color(0xFF1E293B),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

                // Batch & Made in KSA
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFF0F172A))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "KSA",
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Bottom subtle reflection edge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFFCBD5E1))
            )
        }
    }
}

@Composable
private fun OfficialFittingDisplay(
    fittingType: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F172A))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        // Authentic Fitting Graphic with Blue Stripe Collar and Red Palm Brand
        Canvas(
            modifier = Modifier.size(width = 240.dp, height = 100.dp)
        ) {
            val w = size.width
            val h = size.height

            // White injection-molded fitting body
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFE2E8F0)),
                    start = Offset(0f, 0f),
                    end = Offset(w, h)
                ),
                topLeft = Offset(w * 0.15f, h * 0.12f),
                size = Size(w * 0.70f, h * 0.76f),
                cornerRadius = CornerRadius(14.dp.toPx(), 14.dp.toPx())
            )

            // Outline
            drawRoundRect(
                color = Color(0xFFCBD5E1),
                topLeft = Offset(w * 0.15f, h * 0.12f),
                size = Size(w * 0.70f, h * 0.76f),
                cornerRadius = CornerRadius(14.dp.toPx(), 14.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )

            // ⭐ THE BLUE STRIPE (خط أزرق محيطي مميز)
            drawRoundRect(
                color = Color(0xFF0077FF),
                topLeft = Offset(w * 0.15f, h * 0.38f),
                size = Size(w * 0.70f, 9.dp.toPx()),
                cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx())
            )
        }

        // Fitting Content: Red Palm Logo + JIZAN + Fitting Name
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White.copy(alpha = 0.95f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                JizanPalmTreeLogo(modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "جيزان JIZAN",
                    color = JizanBrandRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(JizanNavyDark)
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "$fittingType • الخط الأزرق المعتمد",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
