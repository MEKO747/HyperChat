# HyperChat ProGuard Rules

# ===================
# 基本配置
# ===================
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions

# ===================
# Kotlin
# ===================
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

# ===================
# Retrofit
# ===================
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# ===================
# OkHttp
# ===================
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# ===================
# Gson
# ===================
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.stream.** { *; }
-keep class com.hyperchat.app.data.remote.** { *; }
-keep class com.hyperchat.app.domain.model.** { *; }

# ===================
# Room
# ===================
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# ===================
# Hilt
# ===================
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ComponentSupplier { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# ===================
# Compose
# ===================
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# ===================
# ML Kit
# ===================
-keep class com.google.mlkit.** { *; }
-dontwarn com.google.mlkit.**

# ===================
# 保持数据模型
# ===================
-keep class com.hyperchat.app.data.local.entity.** { *; }
-keep class com.hyperchat.app.domain.model.** { *; }

# ===================
# 移除日志
# ===================
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
