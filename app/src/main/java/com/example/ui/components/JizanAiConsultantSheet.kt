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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ChatMessage
import com.example.data.QuoteOrderItem
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JizanAiConsultantSheet(
    onDismissRequest: () -> Unit,
    onAddItemsToQuote: (List<QuoteOrderItem>) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    var inputText by remember { mutableStateOf("") }
    var isThinking by remember { mutableStateOf(false) }

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                isUser = false,
                message = "أهلاً بك! أنا المهندس الذكي لمصنع جيزان للصناعات البلاستيكية. كيف يمكنني مساعدتك في حساب كميات مشروعك أو اختيار مواصفات مواسير JIZAN وتوصيلات M&B بالخط الأزرق؟",
                time = "الآن",
                suggestedItems = emptyList()
            )
        )
    }

    fun handleSend(text: String) {
        if (text.isBlank()) return
        val userMsg = text.trim()
        inputText = ""
        messages.add(
            ChatMessage(
                isUser = true,
                message = userMsg,
                time = "الآن"
            )
        )

        isThinking = true
        coroutineScope.launch {
            delay(900) // Simulated AI reasoning delay
            val lower = userMsg.lowercase()

            val replyText: String
            val suggestions: List<QuoteOrderItem>

            when {
                lower.contains("مياه") || lower.contains("شرب") || lower.contains("تغذية") -> {
                    replyText = "لمشاريع شبكات مياه الشرب، نوصي باعتماد مواسير UPVC فئة PN 10 أو PN 16 المطابقة لمواصفة ASTM D1785، مع توصيلات M&B ذات حلقة الخط الأزرق لضمان تحمل الضغط ومنع التسريب. إليك مقترح الكميات الأنسب لخط التغذية الأساسي:"
                    suggestions = listOf(
                        QuoteOrderItem("مواسير UPVC لمياه الشرب (ASTM D1785)", "4 بوصة (110 مم)", 40, "ماسورة (6م)", "JIZAN", "مواسير", "PN 10"),
                        QuoteOrderItem("كوع UPVC 90° بالخط الأزرق", "4 بوصة (110 مم)", 10, "قطعة", "M&B", "توصيلات", "PN 16"),
                        QuoteOrderItem("جلبة توصيل مستقيمة بالخط الأزرق", "4 بوصة (110 مم)", 12, "قطعة", "M&B", "توصيلات", "PN 16")
                    )
                }
                lower.contains("ري") || lower.contains("زراع") || lower.contains("مزرع") || lower.contains("نخل") -> {
                    replyText = "لمشاريع الري الزراعي والمزارع في البيئات الحارة، نوصي بمواسير UPVC المدعمة بمثبتات الأشعة فوق البنفسجية UV لضمان عدم تأثرها بالشمس والأسمدة، مع تيهات M&B لتفريغ الخطوط بكفاءة:"
                    suggestions = listOf(
                        QuoteOrderItem("مواسير UPVC للري الزراعي (مقاوم للأشعة UV)", "3 بوصة (90 مم)", 60, "ماسورة (6م)", "JIZAN", "مواسير", "PN 6"),
                        QuoteOrderItem("تيه UPVC متساوي بالخط الأزرق", "3 بوصة (90 مم)", 15, "قطعة", "M&B", "توصيلات", "PN 16"),
                        QuoteOrderItem("كوع UPVC 90° بالخط الأزرق", "3 بوصة (90 مم)", 8, "قطعة", "M&B", "توصيلات", "PN 16")
                    )
                }
                lower.contains("صرف") || lower.contains("سيول") || lower.contains("مجاري") || lower.contains("أمطار") -> {
                    replyText = "لشبكات الصرف الصحي وتصريف مياه السيول، نوصي بمواسير UPVC فئة Class BD لمقاومة الأحماض والمنظفات المنزلية بأسطح داخلية ملساء تمنع الانسداد تماماً:"
                    suggestions = listOf(
                        QuoteOrderItem("مواسير UPVC للصرف الصحي والسيول (Class BD)", "6 بوصة (160 مم)", 30, "ماسورة (6م)", "JIZAN", "مواسير", "Class BD"),
                        QuoteOrderItem("مواسير UPVC للصرف الصحي والسيول (Class BD)", "4 بوصة (110 مم)", 40, "ماسورة (6م)", "JIZAN", "مواسير", "Class BD"),
                        QuoteOrderItem("كوع UPVC زاوية 45° بالخط الأزرق", "4 بوصة (110 مم)", 14, "قطعة", "M&B", "توصيلات", "صرف")
                    )
                }
                else -> {
                    replyText = "تم تحليل متطلبات مشروعك. يقدم مصنع جيزان للصناعات البلاستيكية حلولاً متكاملة معتمدة تشمل مواسير UPVC وتوصيلات M&B بالخط الأزرق. إليك حزمة البداية المقترحة لمشروعك، ويمكنك إضافتها مباشرة لنموذج التسعيرة:"
                    suggestions = listOf(
                        QuoteOrderItem("مواسير UPVC لمياه الشرب (ASTM D1785)", "2 بوصة (63 مم)", 30, "ماسورة (6م)", "JIZAN", "مواسير", "PN 10"),
                        QuoteOrderItem("كوع UPVC 90° بالخط الأزرق", "2 بوصة (63 مم)", 8, "قطعة", "M&B", "توصيلات", "PN 16")
                    )
                }
            }

            messages.add(
                ChatMessage(
                    isUser = false,
                    message = replyText,
                    time = "الآن",
                    suggestedItems = suggestions
                )
            )
            isThinking = false
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = modifier.testTag("ai_consultant_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
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
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(JizanNavyPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "استشاري مبيعات جيزان الهندسي (AI)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = JizanNavyPrimary
                        )
                        Text(
                            text = "مساعد ذكي لحساب الكميات واختيار فئات الضغط المعتمدة",
                            fontSize = 10.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                IconButton(onClick = onDismissRequest) {
                    Icon(Icons.Default.Close, contentDescription = "إغلاق", tint = Color(0xFF64748B))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Prompt Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                QuickPromptChip("💧 مياه شرب 4 بوصة", onClick = { handleSend("أحتاج مواسير مياه شرب 4 بوصة وتوصيلاتها") })
                QuickPromptChip("🌱 ري زراعي 100م", onClick = { handleSend("أريد حساب كميات تمديد ري لمزرعة 100 متر") })
                QuickPromptChip("♻️ صرف صحي", onClick = { handleSend("ما هي المواسير المناسبة للصرف وتصريف السيول؟") })
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Chat Messages Thread
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 380.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(messages, key = { it.id }) { msg ->
                    ChatBubble(
                        message = msg,
                        onAddSuggestions = {
                            onAddItemsToQuote(msg.suggestedItems)
                            onDismissRequest()
                        }
                    )
                }

                if (isThinking) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(16.dp), color = JizanElectricBlue, strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("جاري استشارة المعايير الهندسية وحساب الكميات...", fontSize = 11.sp, color = Color(0xFF64748B))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Input Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("اكتب استفسارك أو مسافة التمديد للمشروع...", fontSize = 11.sp) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("input_ai_chat"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = JizanElectricBlue
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { handleSend(inputText) },
                    colors = ButtonDefaults.buttonColors(containerColor = JizanNavyPrimary),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(12.dp),
                    modifier = Modifier.testTag("btn_send_ai_chat")
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "إرسال", modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun QuickPromptChip(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE2E8F0))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JizanNavyPrimary)
    }
}

@Composable
private fun ChatBubble(
    message: ChatMessage,
    onAddSuggestions: () -> Unit
) {
    val isUser = message.isUser
    val bgColor = if (isUser) JizanNavyPrimary else Color.White
    val textColor = if (isUser) Color.White else Color(0xFF1E293B)
    val alignment = if (isUser) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 14.dp,
                        topEnd = 14.dp,
                        bottomStart = if (isUser) 14.dp else 2.dp,
                        bottomEnd = if (isUser) 2.dp else 14.dp
                    )
                )
                .background(bgColor)
                .border(if (isUser) 0.dp else 1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = message.message,
                    fontSize = 12.sp,
                    color = textColor,
                    lineHeight = 18.sp
                )

                // If message contains suggested items, render one-tap add button
                if (message.suggestedItems.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF0FDF4))
                            .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "📦 المواد والكميات المقترحة للمشروع:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = QualityGreen
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        message.suggestedItems.forEach { item ->
                            Text(
                                text = "• ${item.productTitle} (${item.size}) - ${item.quantity} ${item.unit}",
                                fontSize = 10.sp,
                                color = Color(0xFF166534)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Button(
                            onClick = onAddSuggestions,
                            colors = ButtonDefaults.buttonColors(containerColor = QualityGreen),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.AddShoppingCart, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("إضافة هذه الأصناف مباشرة لطلب التسعيرة", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
