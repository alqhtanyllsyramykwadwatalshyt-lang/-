package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.CommercialScene
import com.example.data.FactoryData
import com.example.ui.components.JizanPalmTreeLogo
import com.example.ui.theme.JizanBrandRed
import com.example.ui.theme.JizanElectricBlue
import com.example.ui.theme.JizanNavyDark
import com.example.ui.theme.JizanNavyPrimary
import com.example.ui.theme.QualityGreen

@Composable
fun CommercialScreen(
    onNavigateToQuote: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Scenes Storyboard, 1: Master Veo Prompt, 2: Full Voiceover Script

    val fullVoiceoverScript = remember {
        """من هنا... تبدأ الجودة.
في جيزان، نصنع حلولاً بلاستيكية بجودة موثوقة وهوية واضحة.
نبدأ باختيار المواد الخام بعناية، وتجهيزها وفق متطلبات الإنتاج والمواصفات الفنية لكل منتج.
خبرات بشرية تتابع كل خطوة... لأن الجودة لا تعتمد على الماكينات وحدها، بل على الدقة في التشغيل والمتابعة.
داخل خطوط الإنتاج، تتحول الخامة إلى مواسير مصنّعة بعناية، من خلال عمليات تشكيل دقيقة تضمن الاستقرار في الأداء والشكل.
ثم تمر المواسير بمرحلة المعايرة والتبريد للحصول على الأبعاد المطلوبة والتشطيب المتقن.
كما نوفّر مجموعة متنوعة من التوصيلات، لتقديم حلول متكاملة لشبكات المياه والتمديدات المختلفة.
هوية واضحة على المنتج... مواسير تحمل اسم جيزان، وتوصيلات بعلامة بصرية مميزة تسهّل التعرف على المنتج الأصلي.
وفي كل مرحلة، نخضع المنتجات لإجراءات فحص ومراقبة، للتأكد من جودة التصنيع ومطابقة المنتج للبيانات الفنية المعتمدة.
بعد ذلك، تُجهّز المنتجات للتعبئة بعناية، لضمان وصولها بحالة ممتازة إلى مواقع العمل والعملاء.
ومن المصنع إلى السوق... نعمل على إيصال منتجات جيزان بكفاءة، لدعم المشاريع السكنية والتجارية والبنية التحتية.
مصنع جيزان للصناعات البلاستيكية... حلول بلاستيكية بجودة موثوقة.
جيزان... صناعة تثق بها."""
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("commercial_screen"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Factory Plant Operations Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_jizan_cinematic_plant),
                        contentDescription = "عمليات خطوط الإنتاج السينمائية بمصنع جيزان",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Gradient overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF0F172A).copy(alpha = 0.35f),
                                        Color(0xFF0F172A).copy(alpha = 0.95f)
                                    )
                                )
                            )
                    )

                    // Overlay Content
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(JizanBrandRed)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("إعلان 90 ثانية رسمي", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(JizanElectricBlue)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("Veo 3 4K Ready", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "جيزان — صناعة تثق بها",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )

                        Text(
                            text = "من المواد الخام وحتى التوريد والتوزيع • خط إنتاج متكامل وهوية واضحة",
                            color = Color(0xFF93C5FD),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Segment Tabs: Storyboard / Master Veo Prompt / Script
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = JizanNavyPrimary,
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Movie, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("المشاهد (12 مشهد)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    modifier = Modifier.testTag("tab_commercial_storyboard")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Videocam, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("أمر Veo 3", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    modifier = Modifier.testTag("tab_commercial_veo")
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Mic, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("التعليق الصوتي", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    modifier = Modifier.testTag("tab_commercial_voiceover")
                )
            }
        }

        // TAB CONTENT
        when (selectedTab) {
            0 -> {
                // Storyboard: 12 Scenes
                items(FactoryData.commercialScenesList, key = { it.sceneNumber }) { scene ->
                    CommercialSceneCard(
                        scene = scene,
                        onCopyPrompt = {
                            clipboardManager.setText(AnnotatedString(scene.generationPrompt))
                            Toast.makeText(context, "تم نسخ أمر توليد المشهد ${scene.sceneNumber}", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

            1 -> {
                // Master Veo Prompt Studio
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
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
                                        imageVector = Icons.Default.Videocam,
                                        contentDescription = null,
                                        tint = JizanElectricBlue,
                                        modifier = Modifier.size(22.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "MASTER VIDEO PROMPT (Veo 3)",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = JizanNavyPrimary
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(FactoryData.MASTER_VEO_VIDEO_PROMPT))
                                        Toast.makeText(context, "تم نسخ أمر Veo 3 بالكامل للحافظة", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.testTag("btn_copy_master_veo_prompt")
                                ) {
                                    Icon(Icons.Default.ContentCopy, contentDescription = "نسخ", tint = JizanElectricBlue)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFF0F172A))
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = FactoryData.MASTER_VEO_VIDEO_PROMPT,
                                    color = Color(0xFFE2E8F0),
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    lineHeight = 16.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "مواصفات الإخراج الفني المطلوبة:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = JizanNavyPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "• الدقة: 4K Ultra Realistic\n• نسبة العرض: 16:9 شاشات ويوتيوب أو 9:16 للريلز\n• الإضاءة: صباحية / ذهبية سينمائية مع انعكاسات أسطح الـ UPVC النظيفة\n• الهوية: خط أزرق مميز على التوصيلات وشعار جيزان بالأحمر",
                                fontSize = 11.sp,
                                color = Color(0xFF475569),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            2 -> {
                // Voiceover Script Reader
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
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
                                        imageVector = Icons.Default.RecordVoiceOver,
                                        contentDescription = null,
                                        tint = JizanBrandRed,
                                        modifier = Modifier.size(22.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "النص الكامل للتعليق الصوتي (Voiceover)",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = JizanNavyPrimary
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(fullVoiceoverScript))
                                        Toast.makeText(context, "تم نسخ نص التعليق الصوتي", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.testTag("btn_copy_voiceover_script")
                                ) {
                                    Icon(Icons.Default.ContentCopy, contentDescription = "نسخ", tint = JizanBrandRed)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFFEF2F2))
                                    .border(1.dp, Color(0xFFFECDD3), RoundedCornerShape(10.dp))
                                    .padding(14.dp)
                            ) {
                                Text(
                                    text = fullVoiceoverScript,
                                    fontSize = 13.sp,
                                    color = Color(0xFF1E293B),
                                    lineHeight = 22.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CommercialSceneCard(
    scene: CommercialScene,
    onCopyPrompt: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("scene_card_${scene.sceneNumber}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header: Scene number + Timeframe
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(JizanNavyPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${scene.sceneNumber}",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = scene.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = JizanNavyPrimary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(JizanElectricBlue.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = scene.timeframe,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = JizanElectricBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Visual Description
            Text(
                text = "المشهد البصري:",
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = Color(0xFF64748B)
            )
            Text(
                text = scene.description,
                fontSize = 11.sp,
                color = Color(0xFF334155),
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Voiceover Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF8FAFC))
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = null,
                        tint = JizanBrandRed,
                        modifier = Modifier
                            .size(15.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "التعليق الصوتي: \"${scene.voiceover}\"",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = JizanBrandRed
                        )
                        Text(
                            text = "النص على الشاشة: ${scene.onScreenText}",
                            fontSize = 10.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Copy Veo Prompt Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = scene.badge,
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8),
                    fontWeight = FontWeight.Medium
                )

                TextButton(
                    onClick = onCopyPrompt,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("نسخ أمر Veo 3 لهذا المشهد", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
