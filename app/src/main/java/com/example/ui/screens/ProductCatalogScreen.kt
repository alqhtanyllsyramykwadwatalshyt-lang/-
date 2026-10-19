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
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.JizanPalmTreeLogo
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

data class CatalogDimensionRow(
    val sizeLabel: String,
    val outerDiameter: String,
    val wallThickness: String,
    val standardLength: String
)

data class CatalogItemData(
    val id: String,
    val isPipe: Boolean,
    val nameArabic: String,
    val nameEnglish: String,
    val applicableStandards: String,
    val materialType: String,
    val operatingPressure: String,
    val dimensionsTable: List<CatalogDimensionRow>,
    val recommendedUses: List<String>,
    val visualIdentityNote: String
)

@Composable
fun ProductCatalogScreen(
    onAddProductToQuote: (productName: String, size: String, qty: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSectionIndex by remember { mutableIntStateOf(0) } // 0: المواسير (Pipes), 1: التوصيلات (Fittings)
    var searchQuery by remember { mutableStateOf("") }

    val pipesCatalog = remember {
        listOf(
            CatalogItemData(
                id = "cat-pipe-water",
                isPipe = true,
                nameArabic = "مواسير UPVC لمياه الشرب والضغوط الهندسية",
                nameEnglish = "UPVC Potable Water Pressure Pipes",
                applicableStandards = "ASTM D1785 (مطبوع رسمياً) • ISO 1452 • DIN 8062",
                materialType = "UPVC نقي عالي الكثافة خالي من الرصاص ومعتمد صحياً",
                operatingPressure = "PN10 (10 بار) و PN16 (16 بار) و Class 4/5",
                dimensionsTable = listOf(
                    CatalogDimensionRow("1 بوصة (32 مم)", "32.0 مم", "2.4 مم", "6.0 أمتار"),
                    CatalogDimensionRow("1 1/2 بوصة (50 مم)", "50.0 مم", "3.0 مم", "6.0 أمتار"),
                    CatalogDimensionRow("2 بوصة (63 مم)", "63.0 مم", "3.8 مم", "6.0 أمتار"),
                    CatalogDimensionRow("3 بوصة (90 مم)", "90.0 مم", "5.4 مم", "6.0 أمتار"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "110.0 مم", "6.6 مم", "6.0 أمتار"),
                    CatalogDimensionRow("6 بوصة (160 مم)", "160.0 مم", "9.5 مم", "6.0 أمتار"),
                    CatalogDimensionRow("8 بوصة (200 مم)", "200.0 مم", "11.9 مم", "6.0 أمتار")
                ),
                recommendedUses = listOf(
                    "شبكات توزيع ونقل مياه الشرب البلدية والسكنية",
                    "خطوط التغذية الرئيسية للمباني والأبراج والمستشفيات",
                    "وصلات المضخات والضغط الهيدروليكي العالي"
                ),
                visualIdentityNote = "خط أزرق طولي ممتد + شعار نخلة جيزان بالأحمر + طباعة ASTM D1785"
            ),
            CatalogItemData(
                id = "cat-pipe-drain",
                isPipe = true,
                nameArabic = "مواسير UPVC للصرف الصحي وتصريف السيول",
                nameEnglish = "UPVC Sanitary Drainage & Sewer Pipes",
                applicableStandards = "ASTM D1785 / ASTM D2665 • Class BD • BS EN 1329",
                materialType = "UPVC متين مقاوم للأحماض والمواد الكيميائية والمنظفات",
                operatingPressure = "انسياب بالجاذبية (Gravity Flow) و Class B/BD",
                dimensionsTable = listOf(
                    CatalogDimensionRow("2 بوصة (50 مم)", "50.0 مم", "2.0 مم", "6.0 / 4.0 أمتار"),
                    CatalogDimensionRow("3 بوصة (75 مم)", "75.0 مم", "2.5 مم", "6.0 / 4.0 أمتار"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "110.0 مم", "3.2 مم", "6.0 / 4.0 أمتار"),
                    CatalogDimensionRow("6 بوصة (160 مم)", "160.0 مم", "4.0 مم", "6.0 / 4.0 أمتار"),
                    CatalogDimensionRow("8 بوصة (200 مم)", "200.0 مم", "4.9 مم", "6.0 / 4.0 أمتار")
                ),
                recommendedUses = listOf(
                    "شبكات الصرف الصحي الداخلية والأعمدة الرأسية للمباني",
                    "خطوط تصريف مياه الأمطار والسيول",
                    "التمديدات الصحية الأرضية والمطابق"
                ),
                visualIdentityNote = "خط أزرق طولي + شعار جيزان JIZAN + المقاس والرمز الكودي للصرف"
            ),
            CatalogItemData(
                id = "cat-pipe-irrig",
                isPipe = true,
                nameArabic = "مواسير UPVC للري الزراعي والمسطحات الخضراء",
                nameEnglish = "UPVC Agricultural Irrigation Pipes",
                applicableStandards = "ASTM D1785 • DIN 8062 • معايير الري الزراعي",
                materialType = "UPVC نقي مضاف إليه مثبتات مقاومة للأشعة فوق البنفسجية UV",
                operatingPressure = "PN 6 (6 بار) و PN 10 (10 بار)",
                dimensionsTable = listOf(
                    CatalogDimensionRow("1 بوصة (32 مم)", "32.0 مم", "1.9 مم", "6.0 أمتار"),
                    CatalogDimensionRow("2 بوصة (63 مم)", "63.0 مم", "2.5 مم", "6.0 أمتار"),
                    CatalogDimensionRow("3 بوصة (90 مم)", "90.0 مم", "3.5 مم", "6.0 أمتار"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "110.0 مم", "4.2 مم", "6.0 أمتار"),
                    CatalogDimensionRow("6 بوصة (160 مم)", "160.0 مم", "6.2 مم", "6.0 أمتار")
                ),
                recommendedUses = listOf(
                    "مشاريع الري المحوري والرشاشات الزراعية",
                    "خطوط نقل المياه من الآبار للمزارع والمحاصيل",
                    "تشجير الحدائق والملاعب والمسطحات العامة"
                ),
                visualIdentityNote = "خط أزرق طولي + شعار جيزان JIZAN + كود مقاومة الشمس UV"
            )
        )
    }

    val fittingsCatalog = remember {
        listOf(
            CatalogItemData(
                id = "cat-fit-elbow",
                isPipe = false,
                nameArabic = "أكواع UPVC زاوية 90° و 45° (Elbows)",
                nameEnglish = "UPVC 90° & 45° Elbows with Signature Blue Ring",
                applicableStandards = "ASTM D2466 • ISO 1452-3 • DIN 8063",
                materialType = "خام حقن قوالب عالي الكثافة معالج هيدروليكياً",
                operatingPressure = "ضغط عالي PN 16 (16 بار) للمياه وضغط تصريف",
                dimensionsTable = listOf(
                    CatalogDimensionRow("1/2 بوصة (20 مم)", "Ø20 مم داخلي", "سماكة 2.8 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("3/4 بوصة (25 مم)", "Ø25 مم داخلي", "سماكة 3.0 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("1 بوصة (32 مم)", "Ø32 مم داخلي", "سماكة 3.2 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("1 1/2 بوصة (50 مم)", "Ø50 مم داخلي", "سماكة 4.0 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("2 بوصة (63 مم)", "Ø63 مم داخلي", "سماكة 4.8 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("3 بوصة (90 مم)", "Ø90 مم داخلي", "سماكة 6.0 مم", "زاوية 90° / 45°"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "Ø110 مم داخلي", "سماكة 7.5 مم", "زاوية 90° / 45°")
                ),
                recommendedUses = listOf(
                    "تغيير مسار خطوط الأنابيب بزاوية 90 أو 45 درجة بانسيابية",
                    "وصلات تغذية المباني والمضخات والخزانات",
                    "تمديدات الصرف الصحي بدون حدوث دوامات أو ترسيب"
                ),
                visualIdentityNote = "جسم أبيض ناصع + حلقة الخط الأزرق المميز + شعار نخلة جيزان الأحمر"
            ),
            CatalogItemData(
                id = "cat-fit-tee",
                isPipe = false,
                nameArabic = "تيهات UPVC متساوية ومسلوبة (Tees)",
                nameEnglish = "UPVC Equal & Reducing Tees with Blue Ring",
                applicableStandards = "ASTM D2466 • ISO 1452-3 • DIN 8063",
                materialType = "خام حقن قوالب نقي فائق المتانة",
                operatingPressure = "PN 16 (16 بار) لشبكات المياه وضغط PN10",
                dimensionsTable = listOf(
                    CatalogDimensionRow("1 بوصة (32 مم)", "Ø32x32x32 مم", "سماكة 3.2 مم", "تفريع متساوي"),
                    CatalogDimensionRow("1 1/2 بوصة (50 مم)", "Ø50x50x50 مم", "سماكة 4.0 مم", "تفريع متساوي"),
                    CatalogDimensionRow("2 بوصة (63 مم)", "Ø63x63x63 مم", "سماكة 4.8 مم", "تفريع متساوي"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "Ø110x110x110 مم", "سماكة 7.5 مم", "تفريع متساوي"),
                    CatalogDimensionRow("4x2 بوصة (مسلوب)", "Ø110x63x110 مم", "سماكة 7.5 مم", "تفريع مسلوب")
                ),
                recommendedUses = listOf(
                    "تفريعات الخطوط الرئيسية إلى خطوط فرعية",
                    "نقاط توزيع الشبكة للوحدات السكنية والحمامات",
                    "شبكات الري المتشعبة"
                ),
                visualIdentityNote = "جسم أبيض + الخط الأزرق الدائري + شعار جيزان JIZAN مطبوع"
            ),
            CatalogItemData(
                id = "cat-fit-coupling",
                isPipe = false,
                nameArabic = "جلب توصيل وسدادات ومحولات سن (Couplings & Adapters)",
                nameEnglish = "UPVC Couplings, Sockets, Plugs & Threaded Adapters",
                applicableStandards = "ASTM D2466 • ISO 1452-3 • خيوط سن قياسية BSPT",
                materialType = "UPVC عالي المتانة ومقاوم للاهتراء الميكانيكي",
                operatingPressure = "PN 16 (16 بار) لجميع المقاسات",
                dimensionsTable = listOf(
                    CatalogDimensionRow("1/2 بوصة (20 مم)", "جلبة ربط Ø20", "سماكة 2.8 مم", "توصيل بالغراء"),
                    CatalogDimensionRow("1 بوصة (32 مم)", "جلبة ربط Ø32", "سماكة 3.2 مم", "توصيل بالغراء"),
                    CatalogDimensionRow("2 بوصة (63 مم)", "جلبة ربط Ø63", "سماكة 4.8 مم", "توصيل بالغراء"),
                    CatalogDimensionRow("4 بوصة (110 مم)", "جلبة ربط Ø110", "سماكة 7.5 مم", "توصيل بالغراء"),
                    CatalogDimensionRow("سدادة ملولبة (Plug)", "سن خارجي 1\" - 4\"", "مسننة بدقة", "إغلاق محكم")
                ),
                recommendedUses = listOf(
                    "وصل أطوال المواسير في المسافات الطويلة",
                    "الربط مع المحابس والعدادات والخلاطات عبر محولات السن",
                    "إغلاق نهايات الخطوط واختبارات الضغط بالسدادات"
                ),
                visualIdentityNote = "حزام الخط الأزرق المميز الدائري + شعار نخلة جيزان لتوثيق الأصالة"
            )
        )
    }

    val currentItems = if (selectedSectionIndex == 0) pipesCatalog else fittingsCatalog
    val filteredItems = remember(searchQuery, currentItems) {
        if (searchQuery.isBlank()) currentItems else {
            currentItems.filter {
                it.nameArabic.contains(searchQuery, ignoreCase = true) ||
                        it.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                        it.dimensionsTable.any { row -> row.sizeLabel.contains(searchQuery) }
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("product_catalog_screen"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Header
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(JizanNavyPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Plumbing,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "كتالوج منتجات مصنع جيزان",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = JizanNavyPrimary
                        )
                        Text(
                            text = "مواصفات الأبعاد، فئات الضغط، المعايير المعتمدة، والهوية البصرية",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }
            }
        }

        // Section Selector: Pipes vs Fittings
        item {
            TabRow(
                selectedTabIndex = selectedSectionIndex,
                containerColor = Color.White,
                contentColor = JizanNavyPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
            ) {
                Tab(
                    selected = selectedSectionIndex == 0,
                    onClick = { selectedSectionIndex = 0 },
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
                                text = "🔴 قسم المواسير (Pipes)",
                                fontWeight = if (selectedSectionIndex == 0) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp
                            )
                        }
                    },
                    modifier = Modifier.testTag("tab_catalog_pipes")
                )
                Tab(
                    selected = selectedSectionIndex == 1,
                    onClick = { selectedSectionIndex = 1 },
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
                                text = "🔵 قسم التوصيلات (Fittings)",
                                fontWeight = if (selectedSectionIndex == 1) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp
                            )
                        }
                    },
                    modifier = Modifier.testTag("tab_catalog_fittings")
                )
            }
        }

        // Section Identity Banner
        item {
            val isPipeSection = selectedSectionIndex == 0
            val cardBg = if (isPipeSection) Color(0xFFFFF1F2) else Color(0xFFEFF6FF)
            val borderCol = if (isPipeSection) Color(0xFFFECDD3) else Color(0xFFBFDBFE)
            val accentCol = if (isPipeSection) JizanBrandRed else JizanElectricBlue

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(cardBg)
                    .border(1.dp, borderCol, RoundedCornerShape(14.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    JizanPalmTreeLogo(modifier = Modifier.size(24.dp), tint = accentCol)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (isPipeSection)
                                "هوية المواسير: خط أزرق على طول المواسير + شعار النخلة بالأحمر + ASTM D1785"
                            else
                                "هوية التوصيلات: جسم أبيض + حلقة الخط الأزرق المميز + شعار جيزان JIZAN",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentCol
                        )
                        Text(
                            text = if (isPipeSection)
                                "تتوفر بأطوال قياسية 6 أمتار و 4 أمتار وفئات ضغط PN6, PN10, PN16"
                            else
                                "مقولبة بالحقن عالي الدقة، وتتحمل ضغوط تشغيل حتى 16 بار (PN 16)",
                            fontSize = 10.sp,
                            color = Color(0xFF475569)
                        )
                    }
                }
            }
        }

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("بحث في الكتالوج (مثال: 110 مم، كوع، تيه، 4 بوصة، PN10...)", fontSize = 12.sp) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color(0xFF64748B))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_catalog_input"),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = JizanElectricBlue,
                    unfocusedBorderColor = Color(0xFFE2E8F0)
                ),
                singleLine = true
            )
        }

        // Catalog Items List
        items(filteredItems, key = { it.id }) { item ->
            CatalogItemDetailCard(
                item = item,
                onAddQuote = { size, qty ->
                    onAddProductToQuote(item.nameArabic, size, qty)
                }
            )
        }
    }
}

@Composable
private fun CatalogItemDetailCard(
    item: CatalogItemData,
    onAddQuote: (size: String, qty: Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedSizeForQuote by remember { mutableStateOf(item.dimensionsTable.firstOrNull()?.sizeLabel ?: "") }
    val themeColor = if (item.isPipe) JizanBrandRed else JizanElectricBlue

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("catalog_item_${item.id}"),
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
            // Header: Name & Identity Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(themeColor.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = if (item.isPipe) "🔴 مواسير JIZAN" else "🔵 توصيلات الخط الأزرق",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = themeColor
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = item.operatingPressure,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.nameArabic,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = JizanNavyPrimary
            )
            Text(
                text = item.nameEnglish,
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Highlights (Standards, Material, Pressure)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF8FAFC))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                SpecInfoRow("المعايير المعتمدة:", item.applicableStandards, QualityGreen)
                SpecInfoRow("نوع الخامة:", item.materialType, JizanNavyPrimary)
                SpecInfoRow("ضغط التشغيل الاسمي:", item.operatingPressure, themeColor)
                SpecInfoRow("العلامة البصرية:", item.visualIdentityNote, Color(0xFF1E40AF))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dimensions Table Preview
            Text(
                text = "جدول الأبعاد والمقاسات القياسية:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = JizanNavyPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Mini Table Component
            DimensionsTableView(
                dimensions = if (isExpanded) item.dimensionsTable else item.dimensionsTable.take(3),
                isPipe = item.isPipe
            )

            if (!isExpanded && item.dimensionsTable.size > 3) {
                Text(
                    text = "عرض باقي المقاسات (+${item.dimensionsTable.size - 3} مقاسات) والاستخدامات ▼",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = JizanElectricBlue,
                    modifier = Modifier
                        .clickable { isExpanded = true }
                        .padding(top = 6.dp)
                )
            }

            // Expanded Section: Recommended Uses & Quote Order
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "الاستخدامات والتطبيقات الموصى بها:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    item.recommendedUses.forEach { use ->
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
                            Text(text = use, fontSize = 11.sp, color = Color(0xFF334155))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Size selector for adding to quote
                    Text(
                        text = "اختر المقاس لإضافته لطلب التسعيرة:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        item.dimensionsTable.take(4).forEach { row ->
                            FilterChip(
                                selected = selectedSizeForQuote == row.sizeLabel,
                                onClick = { selectedSizeForQuote = row.sizeLabel },
                                label = { Text(row.sizeLabel, fontSize = 9.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = JizanNavyPrimary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            onAddQuote(selectedSizeForQuote.ifBlank { item.dimensionsTable.first().sizeLabel }, 20)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_catalog_add_quote_${item.id}")
                    ) {
                        Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("إضافة كمية (20 وحدة) إلى مسودة التسعيرة", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "إغلاق التفاصيل ▲",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B),
                        modifier = Modifier
                            .clickable { isExpanded = false }
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DimensionsTableView(
    dimensions: List<CatalogDimensionRow>,
    isPipe: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
    ) {
        // Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F5F9))
                .padding(horizontal = 8.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "المقاس الاسمي", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary, modifier = Modifier.weight(1.2f))
            Text(text = "القطر الخارجي", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary, modifier = Modifier.weight(1f))
            Text(text = "سماكة الجدار", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary, modifier = Modifier.weight(1f))
            Text(text = if (isPipe) "الطول" else "النوع", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary, modifier = Modifier.weight(1f))
        }

        HorizontalDivider(color = Color(0xFFE2E8F0))

        dimensions.forEachIndexed { index, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) Color.White else Color(0xFFFAFAFA))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = row.sizeLabel, fontSize = 9.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1E293B), modifier = Modifier.weight(1.2f))
                Text(text = row.outerDiameter, fontSize = 9.sp, color = Color(0xFF475569), modifier = Modifier.weight(1f))
                Text(text = row.wallThickness, fontSize = 9.sp, color = Color(0xFF475569), modifier = Modifier.weight(1f))
                Text(text = row.standardLength, fontSize = 9.sp, color = JizanElectricBlue, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            }
            if (index < dimensions.size - 1) {
                HorizontalDivider(color = Color(0xFFF1F5F9))
            }
        }
    }
}

@Composable
private fun SpecInfoRow(
    label: String,
    value: String,
    highlightColor: Color = JizanNavyPrimary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF475569),
            modifier = Modifier.width(115.dp)
        )
        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = highlightColor
        )
    }
}
