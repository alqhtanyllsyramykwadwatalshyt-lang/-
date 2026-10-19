package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuoteOrderItem
import com.example.ui.components.AppHeader
import com.example.ui.components.JizanAiConsultantSheet
import com.example.ui.screens.CommercialScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ManufacturingScreen
import com.example.ui.screens.ProductCatalogScreen
import com.example.ui.screens.QuoteScreen
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Ensure natural Arabic Right-to-Left orientation for the whole app
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    JizanFactoryApp()
                }
            }
        }
    }
}

data class NavTabItem(
    val title: String,
    val icon: ImageVector,
    val testTag: String
)

@Composable
fun JizanFactoryApp() {
    var currentTabIndex by remember { mutableIntStateOf(0) }
    var isAiConsultantOpen by remember { mutableStateOf(false) }

    // Persistent quotation items across the app session
    val quoteItems = remember {
        mutableStateListOf(
            QuoteOrderItem(
                productTitle = "مواسير UPVC لمياه الشرب (ASTM D1785)",
                size = "4 بوصة (110 مم)",
                quantity = 40,
                unit = "ماسورة (6م)",
                brand = "JIZAN",
                category = "مواسير",
                pressureRating = "PN 10"
            ),
            QuoteOrderItem(
                productTitle = "كوع UPVC 90° بالخط الأزرق",
                size = "4 بوصة (110 مم)",
                quantity = 15,
                unit = "قطعة",
                brand = "M&B",
                category = "توصيلات",
                pressureRating = "PN 16"
            )
        )
    }

    // Handle Android system back button: return to Home if on secondary tab
    BackHandler(enabled = currentTabIndex != 0) {
        currentTabIndex = 0
    }

    val navItems = listOf(
        NavTabItem("الرئيسية", Icons.Default.Home, "nav_home"),
        NavTabItem("الكتالوج", Icons.Default.MenuBook, "nav_catalog"),
        NavTabItem("إعلان 90ث", Icons.Default.Movie, "nav_commercial"),
        NavTabItem("التصنيع", Icons.Default.PrecisionManufacturing, "nav_manufacturing"),
        NavTabItem("طلب تسعيرة", Icons.Default.Calculate, "nav_quote")
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppHeader()
        },
        floatingActionButton = {
            if (currentTabIndex != 4) {
                ExtendedFloatingActionButton(
                    onClick = { isAiConsultantOpen = true },
                    containerColor = JizanNavyPrimary,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = JizanElectricBlue,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    text = {
                        Text(
                            text = "استشاري المبيعات الذكي",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier.testTag("fab_ai_consultant")
                )
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_nav_bar"),
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = currentTabIndex == index
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentTabIndex = index },
                        icon = {
                            if (index == 4 && quoteItems.isNotEmpty()) {
                                BadgedBox(badge = {
                                    Badge(
                                        containerColor = JizanBrandRed,
                                        contentColor = Color.White
                                    ) {
                                        Text("${quoteItems.size}", fontSize = 10.sp)
                                    }
                                }) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = JizanNavyPrimary,
                            indicatorColor = JizanElectricBlue,
                            unselectedIconColor = Color(0xFF64748B),
                            unselectedTextColor = Color(0xFF64748B)
                        ),
                        modifier = Modifier.testTag(item.testTag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8FAFC))
        ) {
            Crossfade(targetState = currentTabIndex, label = "tab_transition") { tabIndex ->
                when (tabIndex) {
                    0 -> HomeScreen(
                        onNavigateToProducts = { currentTabIndex = 1 },
                        onNavigateToStages = { currentTabIndex = 3 },
                        onNavigateToApplications = { currentTabIndex = 1 },
                        onNavigateToQuote = { currentTabIndex = 4 },
                        onNavigateToCommercial = { currentTabIndex = 2 }
                    )
                    1 -> ProductCatalogScreen(
                        onAddProductToQuote = { name, size, qty ->
                            val existingIndex = quoteItems.indexOfFirst { it.productTitle == name && it.size == size }
                            if (existingIndex >= 0) {
                                val old = quoteItems[existingIndex]
                                quoteItems[existingIndex] = old.copy(quantity = old.quantity + qty)
                            } else {
                                val brand = if (name.contains("توصيل") || name.contains("كوع") || name.contains("تيه") || name.contains("جلب")) "M&B" else "JIZAN"
                                val cat = if (brand == "M&B") "توصيلات" else "مواسير"
                                quoteItems.add(
                                    QuoteOrderItem(
                                        productTitle = name,
                                        size = size,
                                        quantity = qty,
                                        brand = brand,
                                        category = cat
                                    )
                                )
                            }
                            currentTabIndex = 4
                        }
                    )
                    2 -> CommercialScreen(
                        onNavigateToQuote = { currentTabIndex = 4 }
                    )
                    3 -> ManufacturingScreen()
                    4 -> QuoteScreen(
                        quoteItems = quoteItems,
                        onAddItem = { name, size, qty ->
                            val existingIndex = quoteItems.indexOfFirst { it.productTitle == name && it.size == size }
                            if (existingIndex >= 0) {
                                val old = quoteItems[existingIndex]
                                quoteItems[existingIndex] = old.copy(quantity = old.quantity + qty)
                            } else {
                                val brand = if (name.contains("توصيل") || name.contains("كوع") || name.contains("تيه") || name.contains("جلب")) "M&B" else "JIZAN"
                                val cat = if (brand == "M&B") "توصيلات" else "مواسير"
                                quoteItems.add(
                                    QuoteOrderItem(
                                        productTitle = name,
                                        size = size,
                                        quantity = qty,
                                        brand = brand,
                                        category = cat
                                    )
                                )
                            }
                        },
                        onAddFullItem = { newItem ->
                            val existingIndex = quoteItems.indexOfFirst { it.productTitle == newItem.productTitle && it.size == newItem.size }
                            if (existingIndex >= 0) {
                                val old = quoteItems[existingIndex]
                                quoteItems[existingIndex] = old.copy(quantity = old.quantity + newItem.quantity)
                            } else {
                                quoteItems.add(newItem)
                            }
                        },
                        onRemoveItem = { index ->
                            if (index in quoteItems.indices) {
                                quoteItems.removeAt(index)
                            }
                        },
                        onUpdateQuantity = { index, newQty ->
                            if (index in quoteItems.indices) {
                                quoteItems[index] = quoteItems[index].copy(quantity = newQty)
                            }
                        }
                    )
                }
            }
        }
    }

    // AI Consultant Sheet Modal
    if (isAiConsultantOpen) {
        JizanAiConsultantSheet(
            onDismissRequest = { isAiConsultantOpen = false },
            onAddItemsToQuote = { suggestedItems ->
                suggestedItems.forEach { newItem ->
                    val existingIndex = quoteItems.indexOfFirst { it.productTitle == newItem.productTitle && it.size == newItem.size }
                    if (existingIndex >= 0) {
                        val old = quoteItems[existingIndex]
                        quoteItems[existingIndex] = old.copy(quantity = old.quantity + newItem.quantity)
                    } else {
                        quoteItems.add(newItem)
                    }
                }
                currentTabIndex = 4
            }
        )
    }
}
