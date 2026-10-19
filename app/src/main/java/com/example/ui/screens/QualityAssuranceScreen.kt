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
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.FactoryData
import com.example.ui.components.JizanPalmTreeLogo
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

@Composable
fun QualityAssuranceScreen(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: خطوات الرقابة, 1: المعايير والمطابقة (ASTM), 2: تتبع الدفعات
    var selectedBatchCode by remember { mutableStateOf("JZ-2026-L2-B0892") }
    var isBatchVerified by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("quality_assurance_screen"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // QA Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = JizanNavyPrimary)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(QualityGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "نظام ضمان الجودة والمقاييس",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                        Text(
                            text = "الجودة أولاً: رقابة صارمة من المادة الخام حتى تتبع الدفعة",
                            fontSize = 12.sp,
                            color = Color(0xFFCBD5E1)
                        )
                    }
                }
            }
        }

        // Navigation TabRow
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
                            text = "خطوات الفحص",
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_qa_steps")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "المعايير (ASTM)",
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_qa_standards")
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = {
                        Text(
                            text = "تتبع الدفعات",
                            fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    },
                    modifier = Modifier.testTag("tab_qa_batch_tracking")
                )
            }
        }

        when (selectedTab) {
            0 -> {
                // Section: Inspection Steps Breakdown
                item {
                    Text(
                        text = "مراحل الفحص وضبط الجودة الشاملة بالمصنع:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary
                    )
                }

                // Step 1: Raw Material Inspection
                item {
                    QAStepCard(
                        stepNumber = 1,
                        title = "فحص المواد الخام المستلمة (Raw Materials Inspection)",
                        subtitle = "التحقق المخبري من نقاء حبيبات PVC / UPVC قبل التصنيع",
                        icon = Icons.Default.Biotech,
                        color = JizanElectricBlue,
                        checks = listOf(
                            "اختبار الكثافة النوعية ومعامل السيولة المنصهرة (MFI).",
                            "التحقق التام من خلو البوليمر من أي شوائب أو رطوبة.",
                            "فحص نسب المثبتات الحرارية ومقاومات الأشعة UV المضافة.",
                            "مطابقة شهادة التحليل الكيميائي لضمان خلو الخامة من الرصاص لصحة مياه الشرب."
                        ),
                        labEquipment = "أجهزة مقياس الكثافة والمجففات الحرارية الدقيقة."
                    )
                }

                // Step 2: Production Monitoring (Dimensional & Finish Quality)
                item {
                    QAStepCard(
                        stepNumber = 2,
                        title = "مراقبة عمليات الإنتاج (In-Process Quality)",
                        subtitle = "مراقبة الأبعاد والتشطيب اللحظية أثناء البثق والحقن",
                        icon = Icons.Default.Straighten,
                        color = JizanNavyPrimary,
                        checks = listOf(
                            "مراقبة الأبعاد: قياس القطر الخارجي بالليزر وسماكة الجدار بالميكروميتر.",
                            "فحص استدارة القطر وعدم البيضاوية (Ovality) على كامل طول الماسورة.",
                            "التحقق من جودة التشطيب: سطح داخلي وخارجي أملس فائق الانسيابية بدون فقاعات.",
                            "معايرة زاوية الشطف 15° لنهايات المواسير لحماية الحلقات وتسهيل الربط بالغراء.",
                            "فحص وضوح طباعة الشعار المعتمد وحزام الخط الأزرق المميز."
                        ),
                        labEquipment = "ماسحات ليزرية متزامنة مع خط البثق وميكرومترات رقمية."
                    )
                }

                // Step 3: Final Product Testing
                item {
                    QAStepCard(
                        stepNumber = 3,
                        title = "اختبارات المنتج النهائي المخبرية (Final Testing)",
                        subtitle = "اختبارات ميكانيكية وهيدروستاتيكية قاسية للعينات",
                        icon = Icons.Default.Speed,
                        color = JizanBrandRed,
                        checks = listOf(
                            "اختبار الضغط الهيدروستاتيكي لمقاومة الانفجار (Hydrostatic Burst Test).",
                            "اختبار الصدم بمطرقة السقوط الحر (Drop Impact Test) للتأكد من عدم الكسر.",
                            "اختبار التسطيح (Flattening Test) لقياس مرونة الجدار تحت الأحمال الثقيلة.",
                            "اختبار التراجع الحراري الطولي (Thermal Reversion Test) عند درجات حرارة تصل إلى 150°م.",
                            "اختبار ثبات الحبر والخط الأزرق لمقاومة الاحتكاك والتعرية."
                        ),
                        labEquipment = "حوض اختبار الضغط المائي الرقمي وغرفة اختبار السقوط الحر."
                    )
                }
            }

            1 -> {
                // Section: Supported Confirmed Standards (ASTM D1785, ISO 1452)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MilitaryTech,
                                    contentDescription = null,
                                    tint = JizanElectricBlue,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "المواصفة المعتمدة المطبوعة: ASTM D1785",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = JizanNavyPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "كما يظهر في الهوية الرسمية لمواسير جيزان: [ UPVC PIPE Ø110mm PN10 ASTM D1785 ]، يلتزم المصنع بالمعيار الأمريكي القياسي لمواسير UPVC لمياه الشرب والضغوط الهندسية، مع تطبيق معايير ISO و DIN المكافئة.",
                                fontSize = 12.sp,
                                color = Color(0xFF1E40AF),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "جدول المطابقة الفنية والاختبارات القياسية:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary
                    )
                }

                item {
                    StandardsComparisonTable()
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = QualityGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "الالتزام بالمواصفات الداخلية والمشاريع",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JizanNavyPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "إذا تطلب مشروعك معياراً مخصصاً (مثل فئات خاصة لوزارة الإسكان، البلديات، أو مشاريع الري الكبرى)، فإن مختبر المصنع يطبق بروتوكولات اختبار مطابقة معتمدة مع إصدار تقارير الجودة لكل توريدة.",
                                fontSize = 11.sp,
                                color = Color(0xFF475569),
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }

            2 -> {
                // Section: Batch Tracking System (تتبع الدفعات)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFEF2F2)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.QrCode,
                                        contentDescription = null,
                                        tint = JizanBrandRed,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "نظام التتبع الرقمي للدفعات (Batch Tracking)",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = JizanNavyPrimary
                                    )
                                    Text(
                                        text = "تتبع دقيق من خط التصنيع إلى موقع استلام العميل",
                                        fontSize = 11.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "تتم طباعة كود دفعي نفاث فريد على كل ماسورة وتثبيته على عبوات التوصيلات. يتيح هذا الكود معرفة:",
                                fontSize = 12.sp,
                                color = Color(0xFF334155)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            val trackPoints = listOf(
                                "تاريخ وساعة الإنتاج بالدقيقة.",
                                "رقم خط البثق (Extrusion Line) واسم الماكينة.",
                                "رقم شحنة حبيبات الـ UPVC والمثبتات المستخدمة.",
                                "اسم مشرف الجودة وختم الاختبار المخبري المعتمد."
                            )

                            trackPoints.forEach { pt ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 2.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = JizanElectricBlue,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = pt, fontSize = 11.sp, color = Color(0xFF475569))
                                }
                            }
                        }
                    }
                }

                // Interactive Batch Inspector Simulator
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("card_batch_verifier"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "محاكي التحقق من رقم الدفعة المطبوع:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = JizanNavyPrimary
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val batches = listOf("JZ-2026-L2-B0892", "JZ-2026-L1-A4105", "JZ-2026-L3-C1920")
                                batches.forEach { b ->
                                    FilterChip(
                                        selected = selectedBatchCode == b,
                                        onClick = { selectedBatchCode = b },
                                        label = { Text(b, fontSize = 10.sp, fontFamily = FontFamily.Monospace) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = JizanNavyPrimary,
                                            selectedLabelColor = Color.White
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Simulated Inspection Certificate
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White)
                                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            JizanPalmTreeLogo(modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "شهادة مطابقة دفعة الإنتاج",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                color = JizanNavyPrimary
                                            )
                                        }

                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(QualityGreen)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "مُجازة مخبرياً ✓",
                                                color = Color.White,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        color = Color(0xFFF1F5F9)
                                    )

                                    BatchDataRow("كود الدفعة", selectedBatchCode)
                                    BatchDataRow("المواصفة القياسية", "ASTM D1785 / UPVC PN10")
                                    BatchDataRow("اختبار الضغط الهيدروستاتيكي", "اجتاز بنجاح عند 24 بار لمدة 60 دقيقة")
                                    BatchDataRow("اختبار الصدم والمقاومة", "0% تشققات تحت السقوط القياسي")
                                    BatchDataRow("حالة الطباعة والخط الأزرق", "متطابقة مع الهوية الرسمية بنسبة 100%")
                                    BatchDataRow("حالة الإفراج", "مُصرّح للتوريد والمشاريع")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QAStepCard(
    stepNumber: Int,
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    checks: List<String>,
    labEquipment: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(color.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = JizanNavyPrimary
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            checks.forEach { check ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = QualityGreen,
                        modifier = Modifier
                            .size(14.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = check,
                        fontSize = 11.sp,
                        color = Color(0xFF334155),
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF8FAFC))
                    .padding(8.dp)
            ) {
                Text(
                    text = "🔬 أدوات المعايرة المستخدمة: $labEquipment",
                    fontSize = 10.sp,
                    color = Color(0xFF475569)
                )
            }
        }
    }
}

@Composable
private fun StandardsComparisonTable() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Table Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF1F5F9))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "الاختبار / المعيار", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = JizanNavyPrimary, modifier = Modifier.weight(1.2f))
                Text(text = "المعيار ASTM/ISO", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = JizanNavyPrimary, modifier = Modifier.weight(1f))
                Text(text = "نتيجة مصنع جيزان", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = QualityGreen, modifier = Modifier.weight(1f))
            }

            HorizontalDivider(color = Color(0xFFE2E8F0))

            TableRowItem(
                test = "ضغط الانفجار الهيدروستاتيكي",
                standard = "ASTM D1785 / PN10",
                result = "مطابق > 24 بار",
                modifier = Modifier.fillMaxWidth()
            )
            TableRowItem(
                test = "مقاومة الصدم (TIR)",
                standard = "ASTM D2444",
                result = "اجتياز تام 0% عيوب",
                modifier = Modifier.fillMaxWidth()
            )
            TableRowItem(
                test = "الانكماش الحراري (150°م)",
                standard = "ISO 1452-2 (≤ 5%)",
                result = "< 1.8% ممتاز",
                modifier = Modifier.fillMaxWidth()
            )
            TableRowItem(
                test = "نقاء خامة UPVC",
                standard = "خالٍ من الرصاص",
                result = "معتمد لمياه الشرب",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TableRowItem(
    test: String,
    standard: String,
    result: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = test, fontSize = 10.sp, color = Color(0xFF334155), modifier = Modifier.weight(1.2f))
        Text(text = standard, fontSize = 10.sp, color = Color(0xFF64748B), modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(4.dp))
                .background(QualityGreen.copy(alpha = 0.12f))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Text(text = result, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = QualityGreen)
        }
    }
}

@Composable
private fun BatchDataRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 11.sp, color = Color(0xFF64748B))
        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = JizanNavyPrimary,
            fontFamily = if (value.contains("JZ-")) FontFamily.Monospace else FontFamily.Default
        )
    }
}
