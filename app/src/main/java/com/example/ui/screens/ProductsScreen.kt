package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.FactoryData
import com.example.data.ProductItem
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

@Composable
fun ProductsScreen(
    onAddProductToQuote: (productName: String, size: String, qty: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf("الكل") }
    var expandedProductId by remember { mutableStateOf<String?>(null) }

    val filteredProducts = remember(searchQuery, selectedCategoryFilter) {
        FactoryData.productsList.filter { item ->
            val matchesCategory = when (selectedCategoryFilter) {
                "المواسير (JIZAN)" -> item.category == "المواسير"
                "التوصيلات (M&B الخط الأزرق)" -> item.category == "التوصيلات"
                else -> true
            }
            val matchesSearch = searchQuery.isBlank() ||
                    item.nameArabic.contains(searchQuery, ignoreCase = true) ||
                    item.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                    item.sizesAvailable.any { it.contains(searchQuery, ignoreCase = true) }
            matchesCategory && matchesSearch
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("products_screen"),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Photographic Showcase Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_products_showcase),
                    contentDescription = "معرض منتجات مصنع جيزان - مواسير وتوصيلات الخط الأزرق",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    JizanNavyDark.copy(alpha = 0.90f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "كتالوج منتجات مواسير وتوصيلات جيزان",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "هندسة أبعاد متناهية الدقة، مواسير UPVC مختومة وتوصيلات الخط الأزرق المميز",
                        color = Color(0xFFCBD5E1),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Search & Filter Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("ابحث عن مقاس (مثال: 110 مم، 4 بوصة، كوع، تيه...)", fontSize = 12.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color(0xFF64748B)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_products_input"),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = JizanElectricBlue,
                        unfocusedBorderColor = Color(0xFFE2E8F0)
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val categories = listOf("الكل", "المواسير (JIZAN)", "التوصيلات (M&B الخط الأزرق)")
                    categories.forEach { cat ->
                        FilterChip(
                            selected = selectedCategoryFilter == cat,
                            onClick = { selectedCategoryFilter = cat },
                            label = { Text(cat, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = if (cat.contains("المواسير")) JizanBrandRed else JizanNavyPrimary,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_chip_$cat")
                        )
                    }
                }
            }
        }

        // Visual Signature Indicator Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF1F5F9))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = JizanElectricBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "دليل الهوية: المواسير تحمل شعار JIZAN المطبوع بالأحمر، والتوصيلات تميزها حلقة الخط الأزرق (M&B).",
                        fontSize = 11.sp,
                        color = Color(0xFF334155),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Products List
        items(filteredProducts, key = { it.id }) { product ->
            val isExpanded = expandedProductId == product.id
            ProductCard(
                product = product,
                isExpanded = isExpanded,
                onToggleExpand = {
                    expandedProductId = if (isExpanded) null else product.id
                },
                onAddQuote = { size, qty ->
                    onAddProductToQuote(product.nameArabic, size, qty)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun ProductCard(
    product: ProductItem,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onAddQuote: (size: String, qty: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isPipe = product.category == "المواسير"
    val badgeColor = if (isPipe) JizanBrandRed else JizanElectricBlue
    val badgeText = if (isPipe) "🔴 ماسورة JIZAN" else "🔵 توصيلة الخط الأزرق"

    var selectedSize by remember { mutableStateOf(product.sizesAvailable.firstOrNull() ?: "") }
    var orderQuantity by remember { mutableStateOf(10) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("product_card_${product.id}"),
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
            // Header: Category Badge & Brand Signature
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(badgeColor.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = badgeText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFF8FAFC))
                        .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = product.brandStamp,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Titles
            Text(
                text = product.nameArabic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = JizanNavyPrimary
            )
            Text(
                text = product.nameEnglish,
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Visual Signature Line
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF8FAFC))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = badgeColor,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "العلامة البصرية: ${product.visualSignature}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1E293B)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Specs row: Pressure classes
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = null,
                    tint = Color(0xFF64748B),
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "فئات الضغط: ${product.pressureClasses.joinToString(" • ")}",
                    fontSize = 11.sp,
                    color = Color(0xFF475569)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sizes chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Straighten,
                    contentDescription = null,
                    tint = Color(0xFF64748B),
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "المقاسات المتوفرة:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF475569)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                product.sizesAvailable.take(3).forEach { size ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = size, fontSize = 10.sp, color = Color(0xFF334155))
                    }
                }
                if (product.sizesAvailable.size > 3) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "+${product.sizesAvailable.size - 3} مقاسات",
                            fontSize = 10.sp,
                            color = JizanElectricBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Expanded Section: Advantages, Uses, Quick Quote Add
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = "المزايا والمواصفات الفنية:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    product.advantages.forEach { adv ->
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
                                text = adv,
                                fontSize = 11.sp,
                                color = Color(0xFF334155),
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Size selector for quotation
                    Text(
                        text = "اختر المقاس لطلب عرض السعر:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        product.sizesAvailable.take(4).forEach { size ->
                            FilterChip(
                                selected = selectedSize == size,
                                onClick = { selectedSize = size },
                                label = { Text(size, fontSize = 10.sp) },
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
                            onAddQuote(if (selectedSize.isNotBlank()) selectedSize else product.sizesAvailable.first(), orderQuantity)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = if (isPipe) JizanBrandRed else JizanElectricBlue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_quote_item_${product.id}")
                    ) {
                        Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("إضافة إلى مسودة طلب التسعيرة (10 قطع)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Expand / Collapse Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpand() }
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isExpanded) "عرض تفاصيل أقل ▲" else "عرض المواصفات والمقاسات الكاملة ▼",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = JizanElectricBlue
                )
            }
        }
    }
}
