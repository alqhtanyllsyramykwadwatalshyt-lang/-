package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.WaterDamage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen
import kotlin.math.ceil

@Composable
fun SpecCalculator(
    onAddOrderToQuote: (item: String, size: String, qty: Int) -> Unit = { _, _, _ -> },
    modifier: Modifier = Modifier
) {
    var selectedAppType by remember { mutableStateOf("شبكات مياه شرب") }
    var pipeLengthInput by remember { mutableStateOf("120") } // meters
    var flowDemandInput by remember { mutableStateOf("15") } // m3/h or units
    var showResults by remember { mutableStateOf(true) }

    val lengthMeters = pipeLengthInput.toDoubleOrNull() ?: 100.0
    val flowVal = flowDemandInput.toDoubleOrNull() ?: 10.0

    // Calculation logic
    val (recDiameterMm, recDiameterInch, recPn, wallThicknessMm) = when (selectedAppType) {
        "شبكات مياه شرب" -> {
            when {
                flowVal > 30 -> Quadruple("160 مم", "6 بوصة", "PN 16", "9.5 مم")
                flowVal > 15 -> Quadruple("110 مم", "4 بوصة", "PN 16", "6.6 مم")
                flowVal > 5 -> Quadruple("63 مم", "2 بوصة", "PN 10", "3.8 مم")
                else -> Quadruple("32 مم", "1 بوصة", "PN 10", "2.4 مم")
            }
        }
        "صرف صحي ومجاري" -> {
            when {
                flowVal > 20 -> Quadruple("200 مم", "8 بوصة", "Class BD", "4.9 مم")
                flowVal > 10 -> Quadruple("160 مم", "6 بوصة", "Class BD", "4.0 مم")
                else -> Quadruple("110 مم", "4 بوصة", "Class B", "3.2 مم")
            }
        }
        "ري زراعي ومزارع" -> {
            when {
                flowVal > 25 -> Quadruple("110 مم", "4 بوصة", "PN 10 (UV)", "5.3 مم")
                flowVal > 10 -> Quadruple("90 مم", "3 بوصة", "PN 6 (UV)", "3.5 مم")
                else -> Quadruple("63 مم", "2 بوصة", "PN 6 (UV)", "2.5 مم")
            }
        }
        else -> Quadruple("50 مم", "1.5 بوصة", "PN 10", "3.0 مم")
    }

    val pipeUnitsCount = ceil(lengthMeters / 6.0).toInt() // Standard pipe length 6m
    val glueCansCount = ceil(pipeUnitsCount / 15.0).toInt().coerceAtLeast(1)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("spec_calculator_card"),
        shape = RoundedCornerShape(20.dp),
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
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = JizanElectricBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "الحاسبة الهندسية للمواسير والكميات",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = JizanNavyPrimary
                    )
                    Text(
                        text = "تحديد القطر الموصى به، سماكة الجدار، وفئة الضغط والمواد المطلوبة",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Application selection
            Text(
                text = "1. نوع المشروع والتطبيق:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val appTypes = listOf("شبكات مياه شرب", "صرف صحي ومجاري", "ري زراعي ومزارع")
                appTypes.forEach { type ->
                    FilterChip(
                        selected = selectedAppType == type,
                        onClick = { selectedAppType = type },
                        label = { Text(type, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = JizanNavyPrimary,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("calc_type_$type")
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Inputs Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = pipeLengthInput,
                    onValueChange = { pipeLengthInput = it },
                    label = { Text("إجمالي الطول (متر)", fontSize = 11.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("calc_length_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = flowDemandInput,
                    onValueChange = { flowDemandInput = it },
                    label = { Text("التدفق (م³ / س)", fontSize = 11.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("calc_flow_input"),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Results Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF8FAFC))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "النتائج الهندسية الموصى بها:",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = JizanNavyPrimary
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(QualityGreen.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "مواصفة مطابقة",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = QualityGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Key Specs Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ResultPill(
                            title = "القطر الاسمي",
                            value = "$recDiameterMm ($recDiameterInch)",
                            color = JizanNavyPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        ResultPill(
                            title = "فئة الضغط",
                            value = recPn,
                            color = JizanBrandRed,
                            modifier = Modifier.weight(1f)
                        )
                        ResultPill(
                            title = "سماكة الجدار",
                            value = wallThicknessMm,
                            color = JizanElectricBlue,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Quantity breakdown
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ResultPill(
                            title = "عدد المواسير (طول 6م)",
                            value = "$pipeUnitsCount ماسورة",
                            color = Color(0xFF334155),
                            modifier = Modifier.weight(1f)
                        )
                        ResultPill(
                            title = "علب غراء UPVC",
                            value = "$glueCansCount عبوة",
                            color = Color(0xFF0D9488),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Recommended fitting note with the Blue Stripe
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFEFF6FF))
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = JizanElectricBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "التوصيلات المطابقة: توصيلات M&B / JIZAN بالخط الأزرق مقاس $recDiameterInch",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1E40AF)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Button to add to quotation
                    Button(
                        onClick = {
                            onAddOrderToQuote("مواسير $selectedAppType ($recDiameterInch)", recDiameterMm, pipeUnitsCount)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JizanNavyPrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_add_to_quote")
                    ) {
                        Text(
                            text = "إضافة هذه الكمية ($pipeUnitsCount ماسورة) إلى طلب التسعيرة",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultPill(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 10.sp,
                color = Color(0xFF64748B)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

private data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
