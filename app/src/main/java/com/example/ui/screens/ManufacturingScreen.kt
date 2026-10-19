package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.FactoryData
import com.example.data.ProductionStage
import com.example.data.RawMaterial
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

@Composable
fun ManufacturingScreen(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: مراحل الإنتاج (6), 1: المواد الخام

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("manufacturing_screen"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Screen Header
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.PrecisionManufacturing,
                        contentDescription = null,
                        tint = JizanElectricBlue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "منظومة التصنيع والمواد الخام",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary
                    )
                }
                Text(
                    text = "سلسلة القيمة الصناعية لمصنع جيزان: من جودة الخامات النقية إلى الفحص الشامل",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Tab Selector
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = JizanNavyPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = "2️⃣ مراحل الإنتاج الستة",
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_stages")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "1️⃣ المواد الخام والخامات",
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_raw_materials")
                )
            }
        }

        if (selectedTab == 0) {
            // Section 2: Six Production Stages
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEFF6FF))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "تدخل الخامات الهندسية دورة تصنيع مؤتمتة من 6 محطات لضمان أقصى درجات الاستقامة وسماكة الجدار وتوثيق هوية جيزان والخط الأزرق.",
                        fontSize = 11.sp,
                        color = Color(0xFF1E40AF),
                        lineHeight = 16.sp
                    )
                }
            }

            items(FactoryData.productionStagesList) { stage ->
                ProductionStageTimelineItem(stage = stage)
            }
        } else {
            // Section 1: Raw Materials
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFEF2F2))
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "يعتمد المصنع على خامات بلاستيكية مخصصة للاستخدامات الهندسية وشبكات مياه الشرب والصرف، مع اختيار الخامة وفق نوع المنتج والاستخدام النهائي.",
                            fontSize = 11.sp,
                            color = Color(0xFF991B1B),
                            lineHeight = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "🎯 هوية المنتج: يظهر شعار JIZAN / جيزان مطبوعاً بوضوح على جسم الماسورة مع المقاس والمواصفة.",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JizanBrandRed
                        )
                    }
                }
            }

            items(FactoryData.rawMaterialsList) { material ->
                RawMaterialCard(material = material)
            }
        }
    }
}

@Composable
private fun ProductionStageTimelineItem(
    stage: ProductionStage
) {
    val stageIcon = when (stage.stageNumber) {
        1 -> Icons.Default.Science
        2 -> Icons.Default.PrecisionManufacturing
        3 -> Icons.Default.Thermostat
        4 -> Icons.Default.Print
        5 -> Icons.Default.ContentCut
        6 -> Icons.Default.FactCheck
        else -> Icons.Default.Layers
    }

    val stageColor = when (stage.stageNumber) {
        4 -> JizanBrandRed // The Branding stage
        6 -> QualityGreen // Quality stage
        else -> JizanElectricBlue
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("stage_card_${stage.stageNumber}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(stageColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${stage.stageNumber}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = stage.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = JizanNavyPrimary
                        )
                        Text(
                            text = stage.subtitle,
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(stageColor.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = stage.visualBadge,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = stageColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stage.description,
                fontSize = 12.sp,
                color = Color(0xFF334155),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Key execution points
            stage.keyPoints.forEach { point ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = stageColor,
                        modifier = Modifier
                            .size(14.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = point,
                        fontSize = 11.sp,
                        color = Color(0xFF475569),
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Technical Note footer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF8FAFC))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = "⚙️ التفصيل الهندسي: ${stage.technicalDetail}",
                    fontSize = 10.sp,
                    color = Color(0xFF475569)
                )
            }
        }
    }
}

@Composable
private fun RawMaterialCard(
    material: RawMaterial
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("material_card_${material.title}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Biotech,
                        contentDescription = null,
                        tint = JizanElectricBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = material.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = JizanNavyPrimary
                    )
                    Text(
                        text = material.subtitle,
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = material.description,
                fontSize = 12.sp,
                color = Color(0xFF334155),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            material.benefits.forEach { benefit ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = QualityGreen,
                        modifier = Modifier
                            .size(14.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = benefit,
                        fontSize = 11.sp,
                        color = Color(0xFF475569),
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF1F5F9))
                    .padding(8.dp)
            ) {
                Text(
                    text = "🎯 دورها بالمنتج النهائي: ${material.roleInFinalProduct}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1E293B)
                )
            }
        }
    }
}
