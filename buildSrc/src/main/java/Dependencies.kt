/**
 * Created by A. A. Sumitro on 17/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object App {
    val id =  "id.aasumitro.firegate"
}

object Releases {
    val version_code = 1
    val version_name = "1.0-SNAPSHOT"
}

object SDK {
    var compile_version = 29
    var target_version = 29
    var minimum_version = 21
    var build_tools_version = "29.0.0"
}

object Version {
    internal var gradle_version = "3.4.2"
    internal var kotlin_version = "1.3.41"
    internal var kotlin_coroutines_version = "1.2.2"
    internal var appcompat_version = "1.0.2"
    internal var ktx_core_version = "1.0.2"
    internal var recycler_view_version = "1.0.0'"
    internal var card_view_version = "1.0.0'"
    internal var constraint_layout_version = "1.1.3"
    internal var lifecycle_version = "2.0.0"
    internal var material = "1.0.0"
    internal var firebase_core = "17.0.1"
    internal var firebase_firestore = "20.1.0"
    internal var google_service_version = "4.3.0"
}

object GradleDeps {
    var gradle_plugin = "com.android.tools.build:gradle:${Version.gradle_version}"
}

object KotlinDeps {
    var gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin_version}"
    var std_lib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin_version}"
    var coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlin_coroutines_version}"
    var coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlin_coroutines_version}"
    var coroutines_play_service = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.kotlin_coroutines_version}"
}

object AndroidXComp {
    var appcompat = "androidx.appcompat:appcompat:${Version.appcompat_version}"
    var ktx = "androidx.core:core-ktx:${Version.ktx_core_version}"
    var constraint_layout = "androidx.constraintlayout:constraintlayout:${Version.constraint_layout_version}"
    var recycler_view = "androidx.recyclerview:recyclerview:${Version.recycler_view_version}"
    var card_view = "androidx.cardview:cardview:${Version.card_view_version}"
}

object ArchCompDeps {
    var extension = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle_version}"
    var compiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle_version}"
}

object MaterialDeps {
    var material = "com.google.android.material:material:${Version.material}"
}

object FirebaseDeps {
    var core = "com.google.firebase:firebase-core:${Version.firebase_core}"
    var firestore = "com.google.firebase:firebase-firestore:${Version.firebase_firestore}"
}

object GmsDeps {
    var google_services = "com.google.gms:google-services:${Version.google_service_version}"
}