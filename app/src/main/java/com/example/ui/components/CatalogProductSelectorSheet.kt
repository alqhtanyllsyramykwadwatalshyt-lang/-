package com.example.ui.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuoteOrderItem
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

data class CatalogPresetItem(
    val title: String,
    val brand: String,
    val category: String,
    val standard: String,
    val defaultPressure: String,
    val defaultUnit: String,
    val availableSizes: List<String>,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogProductSelectorSheet(
    onDismissRequest: () -> Unit,
    onProductSelected: (QuoteOrderItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedCategoryTab by remember { mutableIntStateOf(0) } // 0: All, 1: Pipes, 2: Fittings
    var searchQuery by remember { mutableStateOf("") }

    val catalogPresets = remember {
        listOf(
            CatalogPresetItem(
                title = "مواسير UPVC لمياه الشرب (ASTM D1785)",
                brand = "JIZAN",
                category = "مواسير",
                standard = "ASTM D1785 • ISO 1452",
                defaultPressure = "PN 10",
                defaultUnit = "ماسورة (6م)",
                availableSizes = listOf("1 بوصة (32 مم)", "1 1/2 بوصة (50 مم)", "2 بوصة (63 مم)", "3 بوصة (90 مم)", "4 بوصة (110 مم)", "6 بوصة (160 مم)", "8 بوصة (200 مم)"),
                description = "مواسير ضغط هندسية للشرب مع خط أزرق وشعار النخلة الأحمر."
            ),
            CatalogPresetItem(
                title = "مواسير UPVC مياه ضغط عالي PN16",
                brand = "JIZAN",
                category = "مواسير",
                standard = "ASTM D1785 Class 5",
                defaultPressure = "PN 16",
                defaultUnit = "ماسورة (6م)",
                availableSizes = listOf("1 1/2 بوصة (50 مم)", "2 بوصة (63 مم)", "3 بوصة (90 مم)", "4 بوصة (110 مم)", "6 بوصة (160 مم)"),
                description = "للضغوط العالية وخطوط التغذية والمضخات الرئيسية."
            ),
            CatalogPresetItem(
                title = "مواسير UPVC للصرف الصحي والسيول (Class BD)",
                brand = "JIZAN",
                category = "مواسير",
                standard = "ASTM D2665 • BS EN 1329",
                defaultPressure = "انسياب بالجاذبية",
                defaultUnit = "ماسورة (6م)",
                availableSizes = listOf("2 بوصة (50 مم)", "3 بوصة (75 مم)", "4 بوصة (110 مم)", "6 بوصة (160 مم)", "8 بوصة (200 مم)"),
                description = "مقاومة عالية للأحماض والمنظفات وغازات الصرف."
            ),
            CatalogPresetItem(
                title = "مواسير UPVC للري الزراعي (مقاوم للأشعة UV)",
                brand = "JIZAN",
                category = "مواسير",
                standard = "ASTM D1785 • DIN 8062",
                defaultPressure = "PN 6",
                defaultUnit = "ماسورة (6م)",
                availableSizes = listOf("1 بوصة (32 مم)", "2 بوصة (63 مم)", "3 بوصة (90 مم)", "4 بوصة (110 مم)", "6 بوصة (160 مم)"),
                description = "مثبتات شمس فائقة لشبكات الري المكشوفة والمزارع."
            ),
            CatalogPresetItem(
                title = "كوع UPVC زاوية 90° بالخط الأزرق",
                brand = "M&B",
                category = "توصيلات",
                standard = "ASTM D2466 • ISO 1452-3",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("1/2 بوصة", "3/4 بوصة", "1 بوصة", "1 1/2 بوصة", "2 بوصة", "3 بوصة", "4 بوصة", "6 بوصة"),
                description = "حلقة الخط الأزرق الدائرية لتوثيق الجودة والأصالة."
            ),
            CatalogPresetItem(
                title = "كوع UPVC زاوية 45° بالخط الأزرق",
                brand = "M&B",
                category = "توصيلات",
                standard = "ASTM D2466 • ISO 1452-3",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("1 بوصة", "1 1/2 بوصة", "2 بوصة", "3 بوصة", "4 بوصة", "6 بوصة"),
                description = "انسياب هيدروليكي ممتاز لتغيير الاتجاه بأقل فاقد ضغط."
            ),
            CatalogPresetItem(
                title = "تيه UPVC متساوي (Equal Tee) بالخط الأزرق",
                brand = "M&B",
                category = "توصيلات",
                standard = "ASTM D2466",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("1 بوصة (32 مم)", "1 1/2 بوصة (50 مم)", "2 بوصة (63 مم)", "3 بوصة (90 مم)", "4 بوصة (110 مم)"),
                description = "تفريعات ثلاثية متساوية للمياه والصرف بتوزيع منتظم."
            ),
            CatalogPresetItem(
                title = "تيه مسلوب (Reducing Tee) بالخط الأزرق",
                brand = "M&B",
                category = "توصيلات",
                standard = "ASTM D2466",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("2x1 بوصة", "3x2 بوصة", "4x2 بوصة", "4x3 بوصة", "6x4 بوصة"),
                description = "لتفريغ وتصغير قطر الخط الفرعي بكفاءة."
            ),
            CatalogPresetItem(
                title = "جلبة توصيل مستقيمة (Coupling / Socket)",
                brand = "M&B",
                category = "توصيلات",
                standard = "ASTM D2466",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("1/2 بوصة", "1 بوصة", "1 1/2 بوصة", "2 بوصة", "3 بوصة", "4 بوصة", "6 بوصة"),
                description = "توصيل أطوال المواسير بالغراء بإحكام تام مانع للتسريب."
            ),
            CatalogPresetItem(
                title = "محول سن ذكر / أنثى (Threaded Adapter)",
                brand = "M&B",
                category = "توصيلات",
                standard = "BSPT Thread • PN 16",
                defaultPressure = "PN 16",
                defaultUnit = "قطعة",
                availableSizes = listOf("1/2 بوصة", "3/4 بوصة", "1 بوصة", "1 1/2 بوصة", "2 بوصة"),
                description = "للربط المباشر مع المحابس والخلاطات والعدادات المعدنية."
            )
        )
    }

    val filteredItems = remember(selectedCategoryTab, searchQuery) {
        catalogPresets.filter { item ->
            val matchesCategory = when (selectedCategoryTab) {
                1 -> item.category == "مواسير"
                2 -> item.category == "توصيلات"
                else -> true
            }
            val matchesSearch = searchQuery.isBlank() ||
                    item.title.contains(searchQuery, ignoreCase = true) ||
                    item.availableSizes.any { it.contains(searchQuery, ignoreCase = true) }
            matchesCategory && matchesSearch
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = modifier.testTag("catalog_selector_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
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
                            text = "اختيار منتجات من كتالوج جيزان",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = JizanNavyPrimary
                        )
                        Text(
                            text = "اختر الصنف والمقاس والكمية لإضافتها لطلب التسعيرة",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                IconButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.testTag("btn_close_catalog_selector")
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "إغلاق", tint = Color(0xFF64748B))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Tabs: All / Pipes / Fittings
            TabRow(
                selectedTabIndex = selectedCategoryTab,
                containerColor = Color.White,
                contentColor = JizanNavyPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = selectedCategoryTab == 0,
                    onClick = { selectedCategoryTab = 0 },
                    text = { Text("الكل", fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
                )
                Tab(
                    selected = selectedCategoryTab == 1,
                    onClick = { selectedCategoryTab = 1 },
                    text = { Text("🔴 مواسير JIZAN", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (selectedCategoryTab == 1) JizanBrandRed else Color.Unspecified) }
                )
                Tab(
                    selected = selectedCategoryTab == 2,
                    onClick = { selectedCategoryTab = 2 },
                    text = { Text("🔵 توصيلات M&B", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (selectedCategoryTab == 2) JizanElectricBlue else Color.Unspecified) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("بحث عن صنف أو مقاس (مثال: 4 بوصة، كوع، PN16...)", fontSize = 11.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF64748B)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_sheet_products"),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = JizanElectricBlue,
                    unfocusedBorderColor = Color(0xFFCBD5E1)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // List of items
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 480.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(filteredItems) { item ->
                    CatalogSelectorCard(
                        preset = item,
                        onAdd = { size, qty, unit, pressure ->
                            onProductSelected(
                                QuoteOrderItem(
                                    productTitle = item.title,
                                    size = size,
                                    quantity = qty,
                                    unit = unit,
                                    brand = item.brand,
                                    category = item.category,
                                    pressureRating = pressure
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogSelectorCard(
    preset: CatalogPresetItem,
    onAdd: (size: String, qty: Int, unit: String, pressure: String) -> Unit
) {
    var selectedSize by remember { mutableStateOf(preset.availableSizes.first()) }
    var quantity by remember { mutableIntStateOf(if (preset.category == "مواسير") 20 else 10) }
    var selectedUnit by remember { mutableStateOf(preset.defaultUnit) }
    var wasAddedRecently by remember { mutableStateOf(false) }

    val isPipe = preset.category == "مواسير"
    val accentColor = if (isPipe) JizanBrandRed else JizanElectricBlue

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(accentColor.copy(alpha = 0.12f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (isPipe) "مواسير JIZAN" else "توصيلات M&B بالخط الأزرق",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = preset.defaultPressure,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF64748B)
                    )
                }

                Text(
                    text = preset.standard,
                    fontSize = 9.sp,
                    color = QualityGreen,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = preset.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = JizanNavyPrimary
            )
            Text(
                text = preset.description,
                fontSize = 10.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Size Selector Chips
            Text(
                text = "اختر المقاس:",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF334155)
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(preset.availableSizes) { sizeOption ->
                    FilterChip(
                        selected = selectedSize == sizeOption,
                        onClick = { selectedSize = sizeOption },
                        label = { Text(sizeOption, fontSize = 9.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = JizanNavyPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Quantity and Add Action Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Quantity Stepper
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                            .clickable { if (quantity > 5) quantity -= 5 },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Remove, contentDescription = "إنقاص", modifier = Modifier.size(14.dp))
                    }

                    Text(
                        text = "$quantity $selectedUnit",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = JizanNavyPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                            .clickable { quantity += 5 },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "زيادة", modifier = Modifier.size(14.dp))
                    }
                }

                Button(
                    onClick = {
                        onAdd(selectedSize, quantity, selectedUnit, preset.defaultPressure)
                        wasAddedRecently = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (wasAddedRecently) QualityGreen else accentColor
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = if (wasAddedRecently) Icons.Default.Check else Icons.Default.AddShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (wasAddedRecently) "تمت الإضافة ✓" else "إضافة للطلب",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
