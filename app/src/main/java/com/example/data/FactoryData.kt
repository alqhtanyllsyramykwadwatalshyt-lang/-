package com.example.data

object FactoryData {
    const val FACTORY_NAME_AR = "مصنع جيزان للصناعات البلاستيكية"
    const val FACTORY_NAME_EN = "JIZAN Plastic Industries Factory"
    const val FACTORY_SLOGAN = "حلول بلاستيكية بجودة موثوقة"
    const val FACTORY_LOCATION = "المملكة العربية السعودية – منطقة جازان"
    const val CONTACT_PHONE = "+966 50 000 0000"
    const val CONTACT_EMAIL = "sales@jizanplastics.com"
    const val WHATSAPP_NUMBER = "966500000000"

    val rawMaterialsList = listOf(
        RawMaterial(
            title = "خامات PVC / UPVC الهندسية",
            subtitle = "البوليمر الأساسي فائق النقاء",
            description = "خامات بلاستيكية مخصصة للاستخدامات الهندسية وشبكات المياه الصالحة للشرب والصرف، تتميز بصلابة فائقة وعدم التفاعل مع السوائل.",
            benefits = listOf(
                "صلابة ميكانيكية استثنائية ومقاومة للضغوط العالية",
                "عمر افتراضي تشغيلي يتجاوز 50 عاماً تحت الأرض",
                "خالية تماماً من الرصاص والملدنات الضارة لصحة مياه الشرب"
            ),
            roleInFinalProduct = "تشكيل الهيكل الهيكلي للماسورة وجدار التوصيلة المانع للتسريب."
        ),
        RawMaterial(
            title = "إضافات ومثبتات حرارية متقدمة",
            subtitle = "Stabilizers & Additives",
            description = "مركبات كيميائية معتمدة تضاف بنسب دقيقة ومدروسة أثناء الخلط لتحسين ثبات الخامة أثناء دورة الانصهار والتشكيل.",
            benefits = listOf(
                "تحسين الاستقرار والمتانة ومقاومة الإجهاد الميكانيكي",
                "منع التدهور الحراري أثناء البثق داخل الماكينات",
                "زيادة قوة الربط والالتصاق بين الجزيئات"
            ),
            roleInFinalProduct = "حماية أسطح المواسير من التفتت والتشقق تحت الأحمال."
        ),
        RawMaterial(
            title = "مقاومات العوامل البيئية والأشعة UV",
            subtitle = "Weather & Thermal Resistance",
            description = "مواد متخصصة تساعد على صمود المنتجات أمام أشعة الشمس والحرارة المرتفعة السائدة في المنطقة وفق أعلى المعايير الفنية.",
            benefits = listOf(
                "مقاومة فائقة لأشعة الشمس فوق البنفسجية في التمديدات الخارجية",
                "تحمل درجات الحرارة العالية بدون فقدان الصلابة أو التقوس",
                "مقاومة التآكل الكيميائي والتربة المالحة"
            ),
            roleInFinalProduct = "ضمان استمرارية أداء المواسير في الظروف المناخية القاسية."
        ),
        RawMaterial(
            title = "أصباغ ومواد تلوين متجانسة عالية الثبات",
            subtitle = "Coloring & Visual Pigments",
            description = "أصباغ متجانسة آمنة للحفاظ على اللون والهوية البصرية الثابتة للمصنع، من اللون الرمادي والأبيض إلى درجات العلامات.",
            benefits = listOf(
                "ثبات لوني يدوم لعقود دون بهتان أو تصبغ",
                "تسهيل التمييز البصري بين خطوط المياه والصرف والري",
                "عنصر أساسي في إبراز الخط الأزرق المميز لتوصيلات جيزان"
            ),
            roleInFinalProduct = "تأصيل الهوية البصرية الرسمية وتمييز المنتج الأصلي بسرعة."
        )
    )

    val productionStagesList = listOf(
        ProductionStage(
            stageNumber = 1,
            title = "تجهيز المواد الخام والخلط",
            subtitle = "وزن إلكتروني وخلط متجانس فائق الدقة",
            description = "يتم تجهيز الخامات وخلط مادة UPVC مع المثبتات والإضافات بنسب علمية محددة حسب نوع المنتج النهائي والمواصفات المستهدفة.",
            keyPoints = listOf(
                "معايرة آلية بنسب دقيقة للأصباغ والمثبتات",
                "خلط ميكانيكي وحراري متجانس في خلاطات مغلقة",
                "سحب عينات دورية لاختبار الكثافة والسيولة قبل التشكيل"
            ),
            technicalDetail = "يتم التحكم بنسب الخلط عبر شاشات تحكم رقمية لمنع أي خلل في مواصفات الدفعة.",
            visualBadge = "مرحلة 1: الخلط"
        ),
        ProductionStage(
            stageNumber = 2,
            title = "التصنيع والتشكيل (البثق والحقن)",
            subtitle = "خطوط بثق وماكينات حقن ألمانية وآسيوية متطورة",
            description = "تدخل الخامة الجاهزة إلى ماكينة الإنتاج، حيث يتم تسخينها وتشكيلها بدقة هندسية إلى مواسير UPVC والتوصيلات بمختلف أشكالها.",
            keyPoints = listOf(
                "تشكيل المواسير عبر اسطوانات البثق المستمر (Extrusion)",
                "حقن التوصيلات (الأكواع، التيهات، الجلب، الوصلات، المحولات)",
                "توزيع متساوٍ ومدروس لسماكة الجدار لمنع نقاط الضعف"
            ),
            technicalDetail = "تنتج خطوطنا: مواسير UPVC، أكواع 45° و 90°، تيهات متساوية ونقاصات، جلب توصيل، ومحولات سن.",
            visualBadge = "مرحلة 2: التشكيل"
        ),
        ProductionStage(
            stageNumber = 3,
            title = "التبريد والمعايرة الفائقة",
            subtitle = "أحواض تبريد تفريغية (Vacuum Calibration)",
            description = "تمر المواسير بمرحلة المعايرة والتبريد بالماء للحصول على القطر الخارجي المحدد والشكل الأسطواني التام بدون أي انحراف.",
            keyPoints = listOf(
                "غرف تفريغ هواء لضبط القطر الدقيق للماسورة",
                "تبريد مائي تدريجي لتثبيت البنية البلورية للـ UPVC",
                "مراقبة استدارة القطر وسمك الجدار على مدار الساعة"
            ),
            technicalDetail = "يضمن نظام التبريد والتفريغ عدم حدوث تشوه بيضاوي في أقطار المواسير وتساوي أسطحها.",
            visualBadge = "مرحلة 3: المعايرة"
        ),
        ProductionStage(
            stageNumber = 4,
            title = "الطباعة والعلامة التجارية",
            subtitle = "هوية جيزان المطبوعة والخط الأزرق المميز",
            description = "تتم طباعة شعار JIZAN / جيزان على المواسير مع البيانات الفنية الكاملة. أما التوصيلات فيتم اعتماد الخط الأزرق الواضح كعلامة أصالة مميزة.",
            keyPoints = listOf(
                "طباعة نفاثة بالأشعة الحرارية لشعار JIZAN / جيزان المتكرر",
                "تدوين القطر، ضغط التشغيل، فئة الماسورة، ورقم الدفعة",
                "تطبيق الخط الأزرق المميز على التوصيلات كعلامة تمييز الأصالة (M&B / JIZAN)"
            ),
            technicalDetail = "الحبر المستخدم مقاوم للخدش والتآكل ليبقى الشعار والبيانات واضحة في موقع المشروع.",
            visualBadge = "مرحلة 4: الهوية"
        ),
        ProductionStage(
            stageNumber = 5,
            title = "القص والتشطيب والتهيئة",
            subtitle = "قص ليزري وشطف احترافي للأطراف (Chamfering)",
            description = "يتم قص المواسير بدقة حسب الأطوال القياسية (4 أمتار أو 6 أمتار)، مع تشطيب الأطراف وعمل شطف لتسهيل عملية التوصيل الميكانيكي أو بالغراء.",
            keyPoints = listOf(
                "مناشير آلية متزامنة تقطع بأطوال قياسية متناهية الدقة",
                "شطف حافة الماسورة بزاوية 15° لتسهيل دخولها بالجلبة والتوصيلة",
                "تنظيف دقيق لإزالة أي شوائب ناتجة عن القص"
            ),
            technicalDetail = "شطف الأطراف يحمي حلقات الربط المطاطية ويمنع إزاحة الغراء أثناء التركيب.",
            visualBadge = "مرحلة 5: التشطيب"
        ),
        ProductionStage(
            stageNumber = 6,
            title = "الفحص وضمان الجودة الشامل",
            subtitle = "اختبارات مخبرية هيدروستاتيكية وأبعاد هندسية",
            description = "يتم فحص المنتجات واختبار عينات عشوائية للتأكد من مطابقتها للمواصفات المعتمدة وسلامة الأبعاد قبل التعبئة والتوريد إلى العملاء والمشاريع.",
            keyPoints = listOf(
                "فحص القطر والسماكة بأجهزة قياس إلكترونية دقيقة",
                "اختبار الضغط الهيدروستاتيكي لمقاومة الانفجار والتسريب",
                "اختبار الصدم ومقاومة الكسر بالسقوط الحر",
                "التغليف الآلي وتوثيق شهادة الدفعة"
            ),
            technicalDetail = "لا تخرج أي دفعة من المصنع بدون اجتياز بروتوكول الجودة والتوقيع المعتمد.",
            visualBadge = "مرحلة 6: الجودة"
        )
    )

    val productsList = listOf(
        ProductItem(
            id = "pipe-water-pn10",
            nameArabic = "مواسير UPVC لشبكات المياه مضغوطة",
            nameEnglish = "UPVC Pressure Pipes (Water Distribution)",
            category = "المواسير",
            brandStamp = "JIZAN | جيزان (مطبوع بالأحمر)",
            visualSignature = "شعار جيزان مطبوع بشكل متكرر + القطر + فئة الضغط",
            sizesAvailable = listOf("20 مم (1/2\")", "25 مم (3/4\")", "32 مم (1\")", "50 مم (1 1/2\")", "63 مم (2\")", "90 مم (3\")", "110 مم (4\")", "160 مم (6\")", "200 مم (8\")"),
            pressureClasses = listOf("PN 6", "PN 10", "PN 16", "Class 3", "Class 4", "Class 5"),
            rawMaterialType = "خام UPVC نقي عالي الكثافة خالي من الرصاص",
            advantages = listOf(
                "سطح داخلي أملس فائق الانسيابية يقلل احتكاك المياه ويوفر الطاقة",
                "مقاومة تامة للتكلس والترسبات الكلسية والصدأ",
                "تحمل الضغوط العالية بدون تمدد أو انفجار",
                "سهولة فائقة في القص والتوصيل بالموقع"
            ),
            recommendedUses = listOf("شبكات توزيع مياه الشرب", "تمديدات الأبراج والمجمعات السكنية", "خطوط التغذية الرئيسية للمصانع")
        ),
        ProductItem(
            id = "pipe-drainage",
            nameArabic = "مواسير UPVC للصرف الصحي والتهوية",
            nameEnglish = "UPVC Drainage & Soil Pipes",
            category = "المواسير",
            brandStamp = "JIZAN | جيزان (مطبوع بالأحمر)",
            visualSignature = "شعار جيزان + المقاس بالبوصة والمليمتر + كود الصرف",
            sizesAvailable = listOf("50 مم (1 1/2\")", "75 مم (2 1/2\")", "110 مم (4\")", "160 مم (6\")", "200 مم (8\")", "250 مم (10\")"),
            pressureClasses = listOf("تصريف بالجاذبية (Gravity Flow)", "فئة خفيفة Class B", "فئة ثقيلة Class BD"),
            rawMaterialType = "UPVC متين مقاوم للأحماض والمواد الكيميائية المنزلية",
            advantages = listOf(
                "مقاومة كاملة لغازات الصرف الصحي (H2S) والأحماض والمنظفات",
                "عزل صوتي متفوق لتدفق المياه داخل الجدران",
                "جدران قوية تقاوم ضغوط الردم والتربة الخارجية",
                "وصلات محكمة تمنع انبعاث أي روائح أو تسريب"
            ),
            recommendedUses = listOf("شبكات الصرف الصحي للمباني", "خطوط تصريف مياه الأمطار والسيول", "أعمدة التهوية الرأسية")
        ),
        ProductItem(
            id = "pipe-irrigation",
            nameArabic = "مواسير UPVC للري الزراعي والمسطحات",
            nameEnglish = "UPVC Agricultural Irrigation Pipes",
            category = "المواسير",
            brandStamp = "JIZAN | جيزان (مطبوع بالأحمر)",
            visualSignature = "شعار جيزان مكرر + مقاومة UV + مواصفة الري",
            sizesAvailable = listOf("32 مم (1\")", "50 مم (1 1/2\")", "63 مم (2\")", "75 مم (2 1/2\")", "90 مم (3\")", "110 مم (4\")", "160 مم (6\")"),
            pressureClasses = listOf("PN 4", "PN 6", "PN 10"),
            rawMaterialType = "UPVC مدعم بمثبتات الأشعة فوق البنفسجية UV",
            advantages = listOf(
                "مقاوم لأشعة الشمس والحرارة الشديدة بالبيئة الزراعية",
                "لا يتأثر بالأسمدة والمبيدات الزراعية والأملاح",
                "مرونة تشغيلية عالية وسرعة في مد الخطوط بالحقول",
                "تكلفة اقتصادية ممتازة مع عمر تشغيلي طويل"
            ),
            recommendedUses = listOf("شبكات الري المحوري والرشاشات", "خطوط الري بالتنقيط للمزارع", "مشاريع تشجير الطرق والحدائق العامة")
        ),
        ProductItem(
            id = "fitting-elbow",
            nameArabic = "كوع UPVC زاوية 90° و 45° مع الخط الأزرق",
            nameEnglish = "UPVC Elbows 90° & 45° with Signature Blue Stripe",
            category = "التوصيلات",
            brandStamp = "M&B / JIZAN",
            visualSignature = "جسم توصيلة أبيض + خط أزرق مميز دائر / طولي",
            sizesAvailable = listOf("1/2 بوصة", "3/4 بوصة", "1 بوصة", "1 1/2 بوصة", "2 بوصة", "3 بوصة", "4 بوصة", "6 بوصة"),
            pressureClasses = listOf("ضغط عالي PN 16", "ضغط متوسط PN 10", "صرف صحي"),
            rawMaterialType = "UPVC هندسي مقولب بالحقن عالي الكثافة",
            advantages = listOf(
                "الخط الأزرق المميز يمنحك تأكيداً فورياً لأصالة المنتج وجودته",
                "انحناء هيدروليكي انسيابي يمنع دوامات المياه وانخفاض الضغط",
                "سماكة جدار مدروسة تتحمل الصدمات المائية ومطرقة الماء",
                "دقة أبعاد تجعل التوصيل بالغراء سريعاً ومحكماً"
            ),
            recommendedUses = listOf("تغيير مسار خطوط المياه والصرف", "وصلات المضخات والخزانات", "شبكات التغذية الداخلية")
        ),
        ProductItem(
            id = "fitting-tee",
            nameArabic = "تيه متساوي ومسلوب (Tee) بالخط الأزرق",
            nameEnglish = "UPVC Equal & Reducing Tees with Blue Stripe",
            category = "التوصيلات",
            brandStamp = "M&B / JIZAN",
            visualSignature = "جسم توصيلة أبيض + خط أزرق مميز + شعار M&B / JIZAN",
            sizesAvailable = listOf("1/2\" إلى 6\" متساوي ومسلوب بمختلف المقاسات"),
            pressureClasses = listOf("PN 16 للمياه", "PN 10", "وصلات تصريف"),
            rawMaterialType = "خام حقن نقي معالج ضد الصدمات",
            advantages = listOf(
                "تفريعات هندسية دقيقة لتوزيع سريان المياه بدون إجهاد هيدروليكي",
                "وجود الخط الأزرق الدال على فحص الجودة المعتمد",
                "توصيل محكم ومتين يضمن عدم التسريب نهائياً",
                "توافر خيارات التفريع المتساوي والتفريع المسلوب للأقطار المختلفة"
            ),
            recommendedUses = listOf("تفرعات خطوط التغذية الرئيسية", "نقاط توزيع الشبكات للغرف والوحدات", "خطوط الري المتشعبة")
        ),
        ProductItem(
            id = "fitting-socket-coupling",
            nameArabic = "جلبة توصيل ومحولات سن (Couplings & Adapters)",
            nameEnglish = "UPVC Couplings, Sockets & Threaded Adapters",
            category = "التوصيلات",
            brandStamp = "M&B / JIZAN",
            visualSignature = "حزام الخط الأزرق المميز + علامة M&B / JIZAN",
            sizesAvailable = listOf("1/2\" حتى 8\" جلب عادية وسن نحاس وسن بلاستيك"),
            pressureClasses = listOf("PN 16", "PN 10"),
            rawMaterialType = "UPVC نقي وسبائك نحاسية معزولة للمحولات",
            advantages = listOf(
                "ربط ماسورتين بنفس القطر بدقة فائقة وبدون أي بروز داخلي",
                "محولات سن ذكر وأنثى للربط الآمن مع المحابس والخلاطات",
                "علامة الخط الأزرق تثبت خضوعها لاختبار العزم والضغط",
                "مقاومة ممتازة للتآكل عند الوصل مع المعادن"
            ),
            recommendedUses = listOf("وصل أطوال المواسير في المسافات الطويلة", "الربط مع عدادات المياه والمحابس", "وصلات صيانة الخطوط")
        )
    )

    val applicationAreasList = listOf(
        ApplicationArea(
            id = "water-networks",
            title = "شبكات المياه الصالحة للشرب",
            subtitle = "نقل وتوزيع المياه النقية",
            description = "حلول متكاملة من مواسير وتوصيلات جيزان تضمن نقاء المياه وعدم تغير طعمها أو رائحتها، مع مقاومة مستمرة لضغوط الضخ والتشغيل.",
            keyUses = listOf(
                "شبكات التوزيع البلدية والحكومية لنقل المياه",
                "خطوط المياه الداخلية والخارجية للمباني السكنية",
                "التمديدات المركزية للأبراج، المجمعات والمستشفيات",
                "خطوط التغذية من محطات التحلية والخزانات الأرضية"
            ),
            recommendedSpec = "مواسير UPVC فئة PN 10 أو PN 16 مع توصيلات M&B بالخط الأزرق."
        ),
        ApplicationArea(
            id = "sanitary-drainage",
            title = "الصرف الصحي والتصريف",
            subtitle = "أنظمة تصريف متينة ومحكمة",
            description = "مواسير متينة تقاوم التآكل الكيميائي الناتج عن المنظفات والمخلفات وغاز كبريتيد الهيدروجين، مع أسطح ملساء تمنع الانسدادات.",
            keyUses = listOf(
                "خطوط الصرف الصحي الرأسية والأفقية للمباني",
                "التمديدات الصحية للحمامات والمطابخ والمغاسل",
                "شبكات تصريف مياه الأمطار والسيول للمنشآت",
                "خطوط الصرف تحت الأرض الممتدة للمطابق الرئيسية"
            ),
            recommendedSpec = "مواسير UPVC الصرف فئة Class B أو Class BD مع تيهات وأكواع انسيابية."
        ),
        ApplicationArea(
            id = "agricultural-irrigation",
            title = "الري والقطاع الزراعي",
            subtitle = "كفاءة نقل المياه للحقول والمزارع",
            description = "أنابيب تتحمل درجات الحرارة والشمس والأسمدة، وتوفر تدفقاً غزيراً مع الحد الأدنى من فقدان الضغط عبر المسافات الشاسعة.",
            keyUses = listOf(
                "شبكات الري بالتنقيط المحمية والمكشوفة",
                "خطوط النقل الرئيسية من الآبار الارتوازية إلى المزارع",
                "مشاريع الري المحوري والرشاشات الزراعية",
                "تشجير وتنسيق الحدائق العامة والمسطحات الخضراء"
            ),
            recommendedSpec = "مواسير UPVC خفيفة ومتوسطة الضغط PN 6 أو PN 10 مقاومة للأشعة UV."
        ),
        ApplicationArea(
            id = "construction-infrastructure",
            title = "الإنشاءات والبنية التحتية",
            subtitle = "أمان طويل الأمد للمشاريع الكبرى",
            description = "تعتمد كبرى شركات المقاولات في المشاريع السكنية والتجارية ومشاريع البنية التحتية على حلول جيزان لسهولة تركيبها وموثوقيتها العالية.",
            keyUses = listOf(
                "المشاريع السكنية والمخططات العمرانية الجديدة",
                "المراكز والمجمعات التجارية والجامعات",
                "المصانع والمنشآت الصناعية والمستودعات الكبرى",
                "مشاريع البنية التحتية وتمديدات الخدمات الأرضية"
            ),
            recommendedSpec = "أنظمة كاملة من مواسير UPVC وتوصيلات M&B المعتمدة."
        )
    )

    val qualityControlPillarsList = listOf(
        QualityControlPillar(
            stepNumber = 1,
            title = "فحص المواد الخام المستلمة",
            description = "اختبار نقاء حبيبات PVC / UPVC والتأكد من خلوها من الرطوبة والشوائب ومطابقة معامل السيولة والكثافة الحجمية.",
            checkMethod = "فحص مخبري لكل شحنة خام قبل إدخالها لصوامع الخلط."
        ),
        QualityControlPillar(
            stepNumber = 2,
            title = "مراقبة أبعاد المواسير والتوصيلات",
            description = "قياس مستمر للأقطار الخارجية والداخلية وسماكة الجدار وتطابق الأبعاد مع التفاوتات المسموح بها عالمياً.",
            checkMethod = "أجهزة ميكروميتر ومعايرة ليزرية رقمية دقيقة أثناء السحب."
        ),
        QualityControlPillar(
            stepNumber = 3,
            title = "التحقق من جودة التشطيب والأسطح",
            description = "فحص نعومة السطح الداخلي والخارجي، استقامة الماسورة، زاوية الشطف 15°، ووضوح طباعة الشعار والخط الأزرق.",
            checkMethod = "فحص بصري وميكانيكي على خط الإنتاج ونهاية التبريد."
        ),
        QualityControlPillar(
            stepNumber = 4,
            title = "اختبار الضغط الهيدروستاتيكي والمتانة",
            description = "تعريض عينات مختارة لضغوط هيدروليكية تفوق ضغط التشغيل الاسمي لفترات زمنية محددة للتأكد من عدم حدوث تسريب أو انفجار.",
            checkMethod = "جهاز اختبار الضغط المائي المخبري القياسي."
        ),
        QualityControlPillar(
            stepNumber = 5,
            title = "مطابقة المواصفات الفنية المعتمدة",
            description = "التأكد من مطابقة الخصائص الميكانيكية والحرارية للمنتج قبل إصدار تصريح الإفراج عن الدفعة وتعبئتها.",
            checkMethod = "توثيق تقرير جودة فني لكل خط تشغيل."
        ),
        QualityControlPillar(
            stepNumber = 6,
            title = "تتبع بيانات الإنتاج والدفعات",
            description = "ترقيم كل تشغيلة برقم دفعة (Batch Number) مطبوع على الماسورة لضمان تتبع تاريخ الإنتاج وتفاصيل الماكينة والمشرف.",
            checkMethod = "نظام ترميز مطبوع نفاث لا يمكن كشطه أو تزويره."
        )
    )

    val futureVisionText = "أن يصبح مصنع جيزان للصناعات البلاستيكية علامة صناعية رائدة وموثوقة محلياً وإقليمياً في مجال الأنابيب والتوصيلات البلاستيكية، من خلال التطوير المستمر للمنتجات، وتبني أحدث تقنيات التصنيع المؤتمتة، والالتزام الراسخ بأعلى معايير الجودة والاستدامة."

    val futureGoalsList = listOf(
        "توسيع خطوط الإنتاج وإدخال تقنيات أتمتة تصنيع متقدمة.",
        "زيادة تنوع المقاسات والتوصيلات لتشمل كافة احتياجات البنية التحتية حتى 400 مم.",
        "تطوير المنتجات البلاستيكية الصديقة للبيئة والمعاد تدويرها صناعياً.",
        "رفع كفاءة التصنيع وتقليل الفاقد لتحقيق أفضل استدامة وجودة سعرية.",
        "تحسين أنظمة الجودة والفحص المخبري والحصول على أرفع شهادات المطابقة.",
        "التوسع في الأسواق المحلية في المملكة والأسواق الإقليمية المجاورة.",
        "بناء علامة تجارية قوية وموثوقة تعبر عن الهوية الوطنية والصناعة السعودية المتميزة."
    )

    const val SALES_EMAIL_CC = "orders@jizanplastics.com"
    const val SALES_DEPARTMENT_PHONE = "+966 17 322 8899"
    const val SALES_HOTLINE_MOBILE = "+966 50 123 4567"

    val saudiCities = listOf(
        "منطقة جازان (المدينة)",
        "صبيا",
        "أبو عريش",
        "صامطة",
        "بيش - المدينة الاقتصادية",
        "الدرب",
        "أبها / عسير",
        "خميس مشيط",
        "نجران",
        "جدة",
        "مكة المكرمة",
        "الرياض",
        "الدمام / الشرقية",
        "المدينة المنورة",
        "تبوك",
        "مدينة / موقع آخر بالمملكة"
    )

    val projectSectors = listOf(
        "شبكات توزيع مياه الشرب البلدية",
        "أعمال السباكة وتغذية المباني السكنية",
        "شبكات الصرف الصحي وتصريف السيول",
        "مشاريع الري الزراعي والمزارع",
        "المشاريع التجارية والمولات والأبراج",
        "المقاولات العامة والبنية التحتية",
        "الحدائق والمساحات الخضراء والتشجير"
    )

    val deliveryTimelines = listOf(
        "عاجل (خلال 48 - 72 ساعة)",
        "توريد مرحلي خلال أسبوع عمل",
        "توريد خلال أسبوعين إلى 30 يوماً",
        "تسعيرة لدراسة مقايسة ومناقصة معتمدة"
    )

    // Master Commercial Script & 12 Scenes
    val commercialScenesList = listOf(
        CommercialScene(
            sceneNumber = 1,
            timeframe = "0–6 ثوانٍ",
            title = "افتتاحية قوية للمصنع",
            description = "لقطة جوية سينمائية للمصنع من الخارج، شاحنات، ساحة تحميل، حركة عمل حقيقية، إضاءة صباحية أو غروب فاخر.",
            voiceover = "من هنا... تبدأ الجودة.",
            onScreenText = "مصنع جيزان للصناعات البلاستيكية • JIZAN Plastic Industries Factory",
            generationPrompt = "Cinematic aerial opening shot of a modern plastic pipe manufacturing factory, active loading yard, trucks, industrial buildings, premium realistic atmosphere, sunrise light, dramatic but clean corporate style, international factory commercial look, ultra realistic, 4K, 16:9.",
            badge = "افتتاحية جوية"
        ),
        CommercialScene(
            sceneNumber = 2,
            timeframe = "6–12 ثانية",
            title = "الهوية والرؤية الصناعية",
            description = "انتقال إلى الشعار داخل بيئة صناعية راقية، ثم دخول الكاميرا إلى داخل المصنع الحديث.",
            voiceover = "في جيزان، نصنع حلولاً بلاستيكية بجودة موثوقة وهوية واضحة.",
            onScreenText = "حلول بلاستيكية بجودة موثوقة",
            generationPrompt = "Elegant cinematic transition from factory exterior into branded industrial interior, premium corporate visuals, the feeling of a trusted plastic manufacturing brand, clean red black white visual identity, realistic lighting, smooth camera movement, 4K.",
            badge = "الهوية والرؤية"
        ),
        CommercialScene(
            sceneNumber = 3,
            timeframe = "12–20 ثانية",
            title = "المواد الخام النقية",
            description = "لقطات ماكرو لحبيبات PVC / UPVC، صوامع الخامات، عامل يفحص المواد، خلط وتجهيز متجانس.",
            voiceover = "نبدأ باختيار المواد الخام بعناية، وتجهيزها وفق متطلبات الإنتاج والمواصفات الفنية لكل منتج.",
            onScreenText = "مواد خام مختارة بعناية",
            generationPrompt = "Ultra realistic macro shots of white PVC and UPVC resin pellets, industrial raw material handling, workers preparing and measuring materials in a clean factory environment, modern plastic production process, premium industrial commercial, 4K.",
            badge = "الخامات النقية"
        ),
        CommercialScene(
            sceneNumber = 4,
            timeframe = "20–28 ثانية",
            title = "الكفاءة البشرية والسلامة",
            description = "عمال ومشرفون داخل المصنع، تجهيزات السلامة، متابعة الشاشات، تنسيق احترافي بين الفريق.",
            voiceover = "خبرات بشرية تتابع كل خطوة... لأن الجودة لا تعتمد على الماكينات وحدها، بل على الدقة في التشغيل والمتابعة.",
            onScreenText = "فريق عمل متخصص",
            generationPrompt = "Cinematic realistic shots of skilled factory workers and supervisors inside a plastic manufacturing facility, safety gear, monitoring machines, teamwork, clean and professional industrial environment, international corporate advertisement style, 4K.",
            badge = "الكوادر المتخصصة"
        ),
        CommercialScene(
            sceneNumber = 5,
            timeframe = "28–40 ثانية",
            title = "بثق وتشكيل المواسير",
            description = "الخامة تدخل ماكينة البثق، تسخين، تشكيل، خروج المواسير البيضاء بشكل انسيابي وهندسي فائق الدقة.",
            voiceover = "داخل خطوط الإنتاج، تتحول الخامة إلى مواسير مصنّعة بعناية، من خلال عمليات تشكيل دقيقة تضمن الاستقرار في الأداء والشكل.",
            onScreenText = "تقنيات تصنيع دقيقة • البثق والتشكيل",
            generationPrompt = "High-end cinematic extrusion line for white PVC pipes, plastic material entering advanced machinery, smooth white pipes emerging from the production line, dramatic industrial lighting, precise engineering feel, ultra realistic, 4K, premium factory commercial.",
            badge = "البثق والتشكيل"
        ),
        CommercialScene(
            sceneNumber = 6,
            timeframe = "40–48 ثانية",
            title = "التبريد والمعايرة الفائقة",
            description = "مرور المواسير خلال خط التبريد المائي والمعايرة التفريغية، مياه متدفقة، أجهزة قياس وضبط دائرية.",
            voiceover = "ثم تمر المواسير بمرحلة المعايرة والتبريد للحصول على الأبعاد المطلوبة والتشطيب المتقن.",
            onScreenText = "معايرة دقيقة • تشطيب متقن",
            generationPrompt = "Cinematic close-up of white PVC pipes passing through cooling and calibration systems, water droplets, precision machinery, industrial quality atmosphere, realistic motion, premium commercial style, 4K.",
            badge = "التبريد والمعايرة"
        ),
        CommercialScene(
            sceneNumber = 7,
            timeframe = "48–58 ثانية",
            title = "تصنيع توصيلات M&B",
            description = "أكواع، تيهات، وصلات، جلب، محولات، خروج قطع بيضاء نظيفة ومقولبة، عرض سريع وأنيق للمنتجات.",
            voiceover = "كما نوفّر مجموعة متنوعة من التوصيلات، لتقديم حلول متكاملة لشبكات المياه والتمديدات المختلفة.",
            onScreenText = "أكواع • تيهات • وصلات • جلب • محولات M&B",
            generationPrompt = "Premium cinematic product and factory sequence showing white PVC fittings including elbows, tees, couplings, sockets and reducers being produced in a modern plant, clean surfaces, organized motion, high detail, 4K.",
            badge = "توصيلات M&B"
        ),
        CommercialScene(
            sceneNumber = 8,
            timeframe = "58–66 ثانية",
            title = "الهوية المطبوعة والخط الأزرق",
            description = "لقطة قريبة لماسورة بيضاء عليها كود JIZAN / جيزان بالأحمر، وتوصيلات بيضاء مع الخط الأزرق المميز الدائري.",
            voiceover = "هوية واضحة على المنتج... مواسير تحمل اسم جيزان، وتوصيلات بعلامة بصرية مميزة تسهّل التعرف على المنتج الأصلي.",
            onScreenText = "هوية واضحة على المنتج • الخط الأزرق المميز",
            generationPrompt = "Luxury cinematic close-up of white PVC pipes with clear professional branding and matching white fittings featuring a distinctive blue line, premium product identity, dramatic studio lighting, black background, realistic product commercial, 4K.",
            badge = "الخط الأزرق"
        ),
        CommercialScene(
            sceneNumber = 9,
            timeframe = "66–74 ثانية",
            title = "الفحص المخبري والجودة",
            description = "فني يفحص القطر والسماكة بأجهزة ميكروميتر دقيقة، اختبارات ضغط هيدروستاتيكية، فحص استقامة وتشطيب الأسطح.",
            voiceover = "وفي كل مرحلة، نخضع المنتجات لإجراءات فحص ومراقبة، للتأكد من جودة التصنيع ومطابقة المنتج للبيانات الفنية المعتمدة.",
            onScreenText = "فحص • مراقبة • جودة معتمدة",
            generationPrompt = "Cinematic quality control sequence in a modern plastic pipe factory, technician measuring white PVC pipes and fittings with precision instruments, inspection of product finishing, clean laboratory and industrial environment, corporate premium style, 4K.",
            badge = "الفحص المخبري"
        ),
        CommercialScene(
            sceneNumber = 10,
            timeframe = "74–80 ثانية",
            title = "التعبئة والتغليف الآمن",
            description = "تجميع حزم المواسير، ربطها بأشرطة حماية، تعبئة التوصيلات في كراتين منظمة تحمل هوية المصنع.",
            voiceover = "بعد ذلك، تُجهّز المنتجات للتعبئة بعناية، لضمان وصولها بحالة ممتازة إلى مواقع العمل والعملاء.",
            onScreenText = "تعبئة منظمة وآمنة للمشاريع",
            generationPrompt = "Premium realistic packaging sequence for white PVC pipes and fittings, bundling, wrapping, organized boxes, clean warehouse environment, industrial efficiency, high-end factory commercial look, 4K.",
            badge = "التغليف الآمن"
        ),
        CommercialScene(
            sceneNumber = 11,
            timeframe = "80–86 ثانية",
            title = "الشحن والأسطول والتوزيع",
            description = "رافعات شوكية، تحميل الشاحنات الكبرى، انطلاق الشاحنات من ساحة المصنع إلى مدن المملكة ومواقع المشاريع.",
            voiceover = "ومن المصنع إلى السوق... نعمل على إيصال منتجات جيزان بكفاءة، لدعم المشاريع السكنية والتجارية والبنية التحتية.",
            onScreenText = "جاهزون للتوريد والتوزيع لكافة المناطق",
            generationPrompt = "Cinematic warehouse and logistics scene with forklifts loading PVC pipes and fittings onto trucks, outgoing shipments from a modern factory, trusted supply chain feeling, international industrial commercial style, ultra realistic, 4K.",
            badge = "الشحن والتوزيع"
        ),
        CommercialScene(
            sceneNumber = 12,
            timeframe = "86–90 ثانية",
            title = "النهاية القوية وشعار جيزان",
            description = "تجميع بصري فاخر للمواسير والتوصيلات وشعار جيزان المضيء وخلفية صناعية فخمة.",
            voiceover = "مصنع جيزان للصناعات البلاستيكية... حلول بلاستيكية بجودة موثوقة. جيزان... صناعة تثق بها.",
            onScreenText = "جيزان | M&B • جودة تصنع المستقبل • صناعة تثق بها",
            generationPrompt = "Hero closing shot of premium white PVC pipes and fittings arranged in a luxury industrial product display, elegant corporate finish, dramatic red black white branding atmosphere, clean premium end frame for a world-class factory commercial, ultra realistic, 4K, 16:9.",
            badge = "الختام الرسمي"
        )
    )

    const val MASTER_VEO_VIDEO_PROMPT = """Create a premium cinematic industrial commercial for:
"مصنع جيزان M&B للصناعات البلاستيكية"
"JIZAN & M&B PLASTIC INDUSTRIES"

Preserve the JIZAN | M&B brand identity exactly.
Maintain the red and dark-blue corporate colors.
PVC pipes must remain clean, white and realistic.
Do not redesign, distort, replace or alter the JIZAN & M&B logo.
Maintain consistent factory architecture, machinery, products and visual identity throughout the entire video.

Duration: 45–60 seconds
Resolution: 4K
Aspect ratio: 16:9
Style: Photorealistic 3D cinematic industrial commercial
Quality: Premium corporate advertising
Lighting: Professional cinematic lighting
Camera: Smooth controlled movements, aerial shots, dolly shots, close-ups and macro shots
Materials: Realistic metal, glossy white PVC, realistic water and industrial surfaces
Atmosphere: Modern, clean, technologically advanced, reliable and powerful"""
}

