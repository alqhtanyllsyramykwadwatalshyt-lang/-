package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.FactoryData
import com.example.data.QuoteOrderItem
import com.example.ui.components.CatalogProductSelectorSheet
import com.example.ui.components.JizanPalmTreeLogo
import com.example.ui.components.SpecCalculator
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    quoteItems: List<QuoteOrderItem>,
    onAddItem: (item: String, size: String, qty: Int) -> Unit,
    onRemoveItem: (index: Int) -> Unit,
    onUpdateQuantity: (index: Int, newQty: Int) -> Unit,
    onAddFullItem: ((QuoteOrderItem) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    // Reference code generated for this quote inquiry session
    val rfqReferenceNumber = remember {
        "RFQ-JZN-${SimpleDateFormat("yyMM", Locale.US).format(Date())}-${Random.nextInt(1000, 9999)}"
    }

    // Sheet state for selecting products from catalog
    var isCatalogSheetOpen by remember { mutableStateOf(false) }
    var isCalculatorExpanded by remember { mutableStateOf(false) }

    // Contact Form States
    var companyOrClientName by remember { mutableStateOf("") }
    var contactPersonName by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }
    var clientEmail by remember { mutableStateOf("") }
    var projectName by remember { mutableStateOf("") }
    var selectedSector by remember { mutableStateOf(FactoryData.projectSectors.first()) }
    var isSectorDropdownExpanded by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf(FactoryData.saudiCities.first()) }
    var isCityDropdownExpanded by remember { mutableStateOf(false) }
    var selectedTimeline by remember { mutableStateOf(FactoryData.deliveryTimelines.first()) }
    var additionalNotes by remember { mutableStateOf("") }

    // Inquiry submission dialog state
    var showSubmissionDialog by remember { mutableStateOf(false) }
    var lastSubmittedReference by remember { mutableStateOf("") }

    // Generate Formal Email Inquiry Content
    fun generateInquiryEmailBody(): String {
        val currentDate = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US).format(Date())
        val sb = StringBuilder()
        sb.appendLine("السادة / إدارة المبيعات والتوريد - مصنع جيزان للصناعات البلاستيكية المحترمين")
        sb.appendLine("السلام عليكم ورحمة الله وبركاته،")
        sb.appendLine("تحية طيبة وبعد،،")
        sb.appendLine()
        sb.appendLine("نرجو منكم التكرم بتزويدنا بعرض أسعار رسمي معتمد وجدول التوريد المتاح وفق بيانات الطلب التالية:")
        sb.appendLine("==================================================")
        sb.appendLine("📄 كود مرجع طلب التسعيرة: $rfqReferenceNumber")
        sb.appendLine("📅 تاريخ الطلب: $currentDate")
        sb.appendLine("🏢 اسم الشركة / العميل: ${companyOrClientName.ifBlank { "غير محدد" }}")
        sb.appendLine("👤 ممثل التواصل: ${contactPersonName.ifBlank { "غير محدد" }}")
        sb.appendLine("📱 جوال التواصل: ${contactPhone.ifBlank { "غير محدد" }}")
        sb.appendLine("✉️ البريد الإلكتروني للعميل: ${clientEmail.ifBlank { "غير محدد" }}")
        sb.appendLine("🏗️ اسم المشروع: ${projectName.ifBlank { "مشروع تمديدات عام" }}")
        sb.appendLine("🏷️ قطاع المشروع: $selectedSector")
        sb.appendLine("📍 مدينة / موقع التوريد: $selectedCity")
        sb.appendLine("⏱️ موعد التوريد المطلوب: $selectedTimeline")
        sb.appendLine("==================================================")
        sb.appendLine()
        sb.appendLine("📦 قائمة الأصناف والمقاسات المطلوبة (جدول الكميات BOQ):")
        if (quoteItems.isEmpty()) {
            sb.appendLine("- لم يتم تحديد أصناف بعد (استفسار مواصفات عامة)")
        } else {
            quoteItems.forEachIndexed { i, item ->
                sb.appendLine("${i + 1}. [${item.brand}] ${item.productTitle}")
                sb.appendLine("   - المقاس: ${item.size} | الفئة: ${item.pressureRating}")
                sb.appendLine("   - الكمية المطلوبة: ${item.quantity} ${item.unit}")
            }
        }
        sb.appendLine()
        if (additionalNotes.isNotBlank()) {
            sb.appendLine("📝 ملاحظات وشروط فنية خاصة:")
            sb.appendLine(additionalNotes)
            sb.appendLine()
        }
        sb.appendLine("==================================================")
        sb.appendLine("نأمل موافاتنا بعرض السعر التفصيلي شاملاً شروط الدفع والتسليم في أقرب وقت.")
        sb.appendLine("شاكرين لكم حسن تعاونكم.")
        sb.appendLine("مرسل عبر: تطبيق مصنع جيزان للصناعات البلاستيكية الرسمي")
        return sb.toString()
    }

    fun generateInquiryEmailSubject(): String {
        val clientLabel = companyOrClientName.ifBlank { "عميل جديد" }
        val projectLabel = projectName.ifBlank { "طلب توريد" }
        return "[طلب تسعيرة رسمي] مصنع جيزان - $projectLabel ($clientLabel) [$rfqReferenceNumber]"
    }

    fun sendAutomatedInquiryEmail() {
        val subject = generateInquiryEmailSubject()
        val body = generateInquiryEmailBody()

        // Create mailto URI with target sales email and CC
        val mailtoUri = Uri.parse("mailto:${FactoryData.CONTACT_EMAIL}?cc=${FactoryData.SALES_EMAIL_CC}&subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
        val emailIntent = Intent(Intent.ACTION_SENDTO, mailtoUri)

        try {
            context.startActivity(emailIntent)
            lastSubmittedReference = rfqReferenceNumber
            showSubmissionDialog = true
        } catch (e: Exception) {
            // Fallback: send via generic text chooser
            val fallbackIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(FactoryData.CONTACT_EMAIL))
                putExtra(Intent.EXTRA_CC, arrayOf(FactoryData.SALES_EMAIL_CC))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            try {
                context.startActivity(Intent.createChooser(fallbackIntent, "إرسال طلب التسعيرة عبر البريد الإلكتروني"))
                lastSubmittedReference = rfqReferenceNumber
                showSubmissionDialog = true
            } catch (e2: Exception) {
                clipboardManager.setText(AnnotatedString(body))
                Toast.makeText(context, "تم نسخ تفاصيل الطلب للحافظة لعدم توفر تطبيق بريد مباشر", Toast.LENGTH_LONG).show()
                lastSubmittedReference = rfqReferenceNumber
                showSubmissionDialog = true
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("quote_screen"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Header Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = JizanNavyPrimary)
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
                            JizanPalmTreeLogo(modifier = Modifier.size(34.dp), tint = Color.White)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "طلب عرض أسعار معتمد (RFQ)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "إرسال مباشر لإدارة مبيعات وتوريد المصنع",
                                    fontSize = 11.sp,
                                    color = Color(0xFFCBD5E1)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White.copy(alpha = 0.15f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = rfqReferenceNumber,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = JizanElectricBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "اختر المنتجات المطلوبة من الكتالوج أو استخدم الحاسبة الهندسية، وسيقوم النظام بتجهيز بريد إلكتروني رسمي وإرساله لفريق المبيعات لموافاتك بأفضل تسعيرة خلال 24 ساعة.",
                        fontSize = 11.sp,
                        color = Color(0xFFE2E8F0),
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // Section 1: Product Selection & Catalog Basket
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_quote_basket"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = JizanNavyPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "سلة المواد المختارة (${quoteItems.size} صنف)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = JizanNavyPrimary
                            )
                        }

                        // Button to Open Catalog Selector Sheet
                        Button(
                            onClick = { isCatalogSheetOpen = true },
                            colors = ButtonDefaults.buttonColors(containerColor = JizanElectricBlue),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("btn_open_catalog_selector")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("اختيار من الكتالوج", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Quick Bundles Presets
                    Text(
                        text = "أو اختر باقة توريد جاهزة وسريعة:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            QuickBundleChip(
                                title = "💧 باقة مياه شرب 4 بوصة",
                                onClick = {
                                    onAddItem("مواسير UPVC لمياه الشرب (ASTM D1785)", "4 بوصة (110 مم)", 50)
                                    onAddItem("كوع UPVC 90° بالخط الأزرق", "4 بوصة (110 مم)", 10)
                                    onAddItem("تيه UPVC متساوي بالخط الأزرق", "4 بوصة (110 مم)", 6)
                                    Toast.makeText(context, "تمت إضافة باقة مياه الشرب للسلة", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                        item {
                            QuickBundleChip(
                                title = "🌱 باقة ري زراعي 3 بوصة",
                                onClick = {
                                    onAddItem("مواسير UPVC للري الزراعي (مقاوم UV)", "3 بوصة (90 مم)", 80)
                                    onAddItem("كوع UPVC 90° بالخط الأزرق", "3 بوصة (90 مم)", 12)
                                    onAddItem("جلبة توصيل مستقيمة بالخط الأزرق", "3 بوصة (90 مم)", 15)
                                    Toast.makeText(context, "تمت إضافة باقة الري الزراعي للسلة", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                        item {
                            QuickBundleChip(
                                title = "♻️ باقة صرف صحي 6 و 4 بوصة",
                                onClick = {
                                    onAddItem("مواسير UPVC للصرف الصحي (Class BD)", "6 بوصة (160 مم)", 30)
                                    onAddItem("مواسير UPVC للصرف الصحي (Class BD)", "4 بوصة (110 مم)", 40)
                                    onAddItem("كوع UPVC 45° بالخط الأزرق", "4 بوصة (110 مم)", 16)
                                    Toast.makeText(context, "تمت إضافة باقة الصرف الصحي للسلة", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Selected Items List
                    if (quoteItems.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = Color(0xFF94A3B8),
                                    modifier = Modifier.size(36.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "لم تقم باختيار أي منتجات بعد.",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = JizanNavyPrimary
                                )
                                Text(
                                    text = "اضغط على زر 'اختيار من الكتالوج' أعلاه لإضافة مواسير JIZAN وتوصيلات M&B بالخط الأزرق.",
                                    fontSize = 11.sp,
                                    color = Color(0xFF64748B),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                                )
                            }
                        }
                    } else {
                        quoteItems.forEachIndexed { index, item ->
                            QuoteItemEnhancedRow(
                                item = item,
                                onIncrement = { onUpdateQuantity(index, item.quantity + 5) },
                                onDecrement = {
                                    if (item.quantity > 5) onUpdateQuantity(index, item.quantity - 5)
                                    else onRemoveItem(index)
                                },
                                onRemove = { onRemoveItem(index) }
                            )
                            if (index < quoteItems.size - 1) {
                                HorizontalDivider(color = Color(0xFFF1F5F9))
                            }
                        }
                    }
                }
            }
        }

        // Section 2: Contact Form (بيانات التواصل والمشروع)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_quote_contact_form"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEFF6FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ReceiptLong,
                                contentDescription = null,
                                tint = JizanElectricBlue,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "بيانات العميل والمشروع لطلب التسعيرة:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = JizanNavyPrimary
                            )
                            Text(
                                text = "ستُرفق هذه البيانات رسمياً في البريد الموجه لمبيعات المصنع",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Company / Client Name
                    OutlinedTextField(
                        value = companyOrClientName,
                        onValueChange = { companyOrClientName = it },
                        label = { Text("اسم الشركة / المقاول / المزارع / العميل *", fontSize = 11.sp) },
                        placeholder = { Text("مثال: شركة المقاولات الحديثة أو مؤسسة الفهد", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.Business, contentDescription = null, tint = Color(0xFF64748B)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_company_name"),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Contact Person Name & Mobile
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = contactPersonName,
                            onValueChange = { contactPersonName = it },
                            label = { Text("اسم المسؤول", fontSize = 11.sp) },
                            placeholder = { Text("م. أحمد", fontSize = 11.sp) },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF64748B)) },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("input_contact_person"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = contactPhone,
                            onValueChange = { contactPhone = it },
                            label = { Text("رقم الجوال *", fontSize = 11.sp) },
                            placeholder = { Text("05XXXXXXXX", fontSize = 11.sp) },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF64748B)) },
                            modifier = Modifier
                                .weight(1.1f)
                                .testTag("input_contact_phone"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Client Email
                    OutlinedTextField(
                        value = clientEmail,
                        onValueChange = { clientEmail = it },
                        label = { Text("البريد الإلكتروني للعميل (لاستلام العرض المالي) *", fontSize = 11.sp) },
                        placeholder = { Text("example@company.com", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFF64748B)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_client_email"),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Project Name
                    OutlinedTextField(
                        value = projectName,
                        onValueChange = { projectName = it },
                        label = { Text("اسم المشروع وموقعه", fontSize = 11.sp) },
                        placeholder = { Text("مثال: مشروع مجمع سكني أو مزرعة النخيل", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF64748B)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_project_name"),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Sector Dropdown
                    ExposedDropdownMenuBox(
                        expanded = isSectorDropdownExpanded,
                        onExpandedChange = { isSectorDropdownExpanded = !isSectorDropdownExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedSector,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("قطاع وتصنيف المشروع", fontSize = 11.sp) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSectorDropdownExpanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .testTag("dropdown_project_sector"),
                            shape = RoundedCornerShape(12.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = isSectorDropdownExpanded,
                            onDismissRequest = { isSectorDropdownExpanded = false }
                        ) {
                            FactoryData.projectSectors.forEach { sector ->
                                DropdownMenuItem(
                                    text = { Text(sector, fontSize = 12.sp) },
                                    onClick = {
                                        selectedSector = sector
                                        isSectorDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // City and Timeline Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // City Dropdown
                        ExposedDropdownMenuBox(
                            expanded = isCityDropdownExpanded,
                            onExpandedChange = { isCityDropdownExpanded = !isCityDropdownExpanded },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = selectedCity,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("مدينة التوريد", fontSize = 11.sp) },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCityDropdownExpanded) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                                    .testTag("dropdown_delivery_city"),
                                shape = RoundedCornerShape(12.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = isCityDropdownExpanded,
                                onDismissRequest = { isCityDropdownExpanded = false }
                            ) {
                                FactoryData.saudiCities.forEach { city ->
                                    DropdownMenuItem(
                                        text = { Text(city, fontSize = 12.sp) },
                                        onClick = {
                                            selectedCity = city
                                            isCityDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Delivery Timeline
                        OutlinedTextField(
                            value = selectedTimeline,
                            onValueChange = { selectedTimeline = it },
                            label = { Text("موعد التوريد", fontSize = 11.sp) },
                            leadingIcon = { Icon(Icons.Default.Timer, contentDescription = null, tint = Color(0xFF64748B)) },
                            modifier = Modifier
                                .weight(1.1f)
                                .testTag("input_delivery_timeline"),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Additional Notes
                    OutlinedTextField(
                        value = additionalNotes,
                        onValueChange = { additionalNotes = it },
                        label = { Text("ملاحظات فنية أو متطلبات خاصة (اختياري)", fontSize = 11.sp) },
                        placeholder = { Text("مثال: مطلوب شهادة اختبار هيدروستاتيكي، أو تسليم في الموقع...", fontSize = 11.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_additional_notes"),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 3
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Notice of Factory Destination
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF0FDF4))
                            .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MarkEmailRead,
                                contentDescription = null,
                                tint = QualityGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "وجهة الإرسال: ${FactoryData.CONTACT_EMAIL}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = QualityGreen
                                )
                                Text(
                                    text = "نسخة متابعة فورية إلى: ${FactoryData.SALES_EMAIL_CC}",
                                    fontSize = 10.sp,
                                    color = Color(0xFF166534)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Primary Action: SEND INQUIRY EMAIL TO FACTORY SALES TEAM
                    Button(
                        onClick = { sendAutomatedInquiryEmail() },
                        colors = ButtonDefaults.buttonColors(containerColor = JizanNavyPrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("btn_send_inquiry_email")
                    ) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "إرسال طلب التسعيرة الرسمي عبر البريد الإلكتروني",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Secondary Action: WhatsApp Dispatch & Share
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val body = generateInquiryEmailBody()
                                val uri = Uri.parse("https://api.whatsapp.com/send?phone=${FactoryData.WHATSAPP_NUMBER}&text=${Uri.encode(body)}")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "لم يتم العثور على تطبيق واتساب", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1.2f)
                                .testTag("btn_send_whatsapp")
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("واتساب المبيعات", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                val body = generateInquiryEmailBody()
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, body)
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(sendIntent, "مشاركة طلب التسعيرة الرسمي"))
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_share_quote")
                        ) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("مشاركة", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        IconButton(
                            onClick = {
                                val body = generateInquiryEmailBody()
                                clipboardManager.setText(AnnotatedString(body))
                                Toast.makeText(context, "تم نسخ تفاصيل طلب التسعيرة للحافظة", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.testTag("btn_copy_quote")
                        ) {
                            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "نسخ", tint = JizanNavyPrimary)
                        }
                    }
                }
            }
        }

        // Section 3: Engineering Calculator Collapsible Helper
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isCalculatorExpanded = !isCalculatorExpanded },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = JizanElectricBlue,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "حاسبة التقدير الهندسي للكميات",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = JizanNavyPrimary
                                )
                                Text(
                                    text = "احسب عدد مواسير 6م وأكواع وتيهات الخط الأزرق حسب طول الخط",
                                    fontSize = 10.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }

                        Icon(
                            imageVector = if (isCalculatorExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color(0xFF64748B)
                        )
                    }

                    AnimatedVisibility(visible = isCalculatorExpanded) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            SpecCalculator(
                                onAddOrderToQuote = onAddItem
                            )
                        }
                    }
                }
            }
        }
    }

    // Modal Bottom Sheet: Catalog Product Selector
    if (isCatalogSheetOpen) {
        CatalogProductSelectorSheet(
            onDismissRequest = { isCatalogSheetOpen = false },
            onProductSelected = { newItem ->
                if (onAddFullItem != null) {
                    onAddFullItem(newItem)
                } else {
                    onAddItem(newItem.productTitle, newItem.size, newItem.quantity)
                }
                Toast.makeText(context, "تمت إضافة ${newItem.productTitle} (${newItem.size}) للطلب", Toast.LENGTH_SHORT).show()
            }
        )
    }

    // Submission Confirmation Dialog
    if (showSubmissionDialog) {
        AlertDialog(
            onDismissRequest = { showSubmissionDialog = false },
            confirmButton = {
                Button(
                    onClick = { showSubmissionDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = JizanNavyPrimary)
                ) {
                    Text("تم، حسناً")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        val body = generateInquiryEmailBody()
                        clipboardManager.setText(AnnotatedString(body))
                        Toast.makeText(context, "تم نسخ تفاصيل الطلب للحافظة", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("نسخ ملخص الطلب")
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = QualityGreen,
                    modifier = Modifier.size(44.dp)
                )
            },
            title = {
                Text(
                    text = "تم تجهيز وإرسال طلب التسعيرة بنجاح",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "كود مرجع الطلب الرسمي: $lastSubmittedReference",
                        fontWeight = FontWeight.Bold,
                        color = JizanNavyPrimary,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "تم فتح تطبيق البريد لإرسال التفاصيل مباشرة إلى:",
                        fontSize = 11.sp,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = "• ${FactoryData.CONTACT_EMAIL}\n• ${FactoryData.SALES_EMAIL_CC}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = JizanElectricBlue
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "سيقوم فريق المبيعات الفني بمراجعة كميات ومواصفات المشروع والرد عليكم بعرض السعر وجدول التوريد خلال 24 ساعة عمل.",
                        fontSize = 11.sp,
                        color = Color(0xFF334155),
                        lineHeight = 16.sp
                    )
                }
            },
            shape = RoundedCornerShape(18.dp),
            containerColor = Color.White
        )
    }
}

@Composable
private fun QuickBundleChip(
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF1F5F9))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Text(text = title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary)
    }
}

@Composable
private fun QuoteItemEnhancedRow(
    item: QuoteOrderItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    val isPipe = item.category == "مواسير" || item.productTitle.contains("مواسير")
    val badgeColor = if (isPipe) JizanBrandRed else JizanElectricBlue

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(badgeColor.copy(alpha = 0.12f))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isPipe) "مواسير JIZAN" else "توصيلات M&B",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.size,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF334155)
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = item.productTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = JizanNavyPrimary
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onDecrement, modifier = Modifier.size(24.dp)) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            }

            Text(
                text = "${item.quantity}",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = JizanNavyPrimary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onIncrement, modifier = Modifier.size(24.dp)) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            }

            Spacer(modifier = Modifier.width(6.dp))

            IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "حذف",
                    tint = Color(0xFFEF4444),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
