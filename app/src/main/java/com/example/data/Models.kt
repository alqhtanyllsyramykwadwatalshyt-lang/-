package com.example.data

data class RawMaterial(
    val title: String,
    val subtitle: String,
    val description: String,
    val benefits: List<String>,
    val roleInFinalProduct: String
)

data class ProductionStage(
    val stageNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyPoints: List<String>,
    val technicalDetail: String,
    val visualBadge: String
)

data class ProductItem(
    val id: String,
    val nameArabic: String,
    val nameEnglish: String,
    val category: String, // "مواسير" أو "توصيلات"
    val brandStamp: String, // "JIZAN | جيزان" أو "M&B / JIZAN"
    val visualSignature: String, // "شعار أحمر مطبوع" أو "خط أزرق مميز"
    val sizesAvailable: List<String>,
    val pressureClasses: List<String>,
    val rawMaterialType: String,
    val advantages: List<String>,
    val recommendedUses: List<String>
)

data class ApplicationArea(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyUses: List<String>,
    val recommendedSpec: String
)

data class QualityControlPillar(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val checkMethod: String
)

data class QuoteOrderItem(
    val productTitle: String,
    val size: String,
    val quantity: Int,
    val unit: String = "ماسورة (6م)",
    val brand: String = "JIZAN",
    val category: String = "مواسير",
    val pressureRating: String = "PN 10",
    val notes: String = ""
)

data class QuoteInquiry(
    val referenceNumber: String,
    val companyName: String,
    val contactPerson: String,
    val phone: String,
    val email: String,
    val projectName: String,
    val projectSector: String,
    val deliveryCity: String,
    val deliveryTimeline: String,
    val additionalNotes: String,
    val items: List<QuoteOrderItem>,
    val createdAtFormatted: String
)

data class CommercialScene(
    val sceneNumber: Int,
    val timeframe: String,
    val title: String,
    val description: String,
    val voiceover: String,
    val onScreenText: String,
    val generationPrompt: String,
    val badge: String
)

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val isUser: Boolean,
    val message: String,
    val time: String,
    val suggestedItems: List<QuoteOrderItem> = emptyList()
)
