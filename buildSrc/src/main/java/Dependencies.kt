/** @Author Kamal Nayan
 **/
object Dependencies {
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}" }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtx}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val lifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
    val livedataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedataKtx}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val junitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExt}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }
    val sandwich by lazy { "com.github.skydoves:sandwich:${Versions.sandwich}" }
    val coroutineAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}" }
    val coroutineCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}" }

    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }

    val epoxy by lazy { "com.airbnb.android:epoxy:${Versions.epoxy}" }
    val epoxyPaging by lazy { "com.airbnb.android:epoxy-paging3:${Versions.epoxy}" }
    val epoxyDataBinding by lazy { "com.airbnb.android:epoxy-databinding:${Versions.epoxy}" }
    val epoxyProcessor by lazy { "com.airbnb.android:epoxy-processor:${Versions.epoxy}" }

    val kotlinStdLib by lazy { "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLib}" }
    val shimmer by lazy { "com.facebook.shimmer:shimmer:${Versions.shimmer}" }

    val room by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomPaging by lazy { "androidx.room:room-paging:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }

    val paging by lazy { "androidx.paging:paging-runtime:${Versions.paging}" }
    val javaxInject by lazy { "javax.inject:javax.inject:1" }
    val kotlinReflection by lazy { "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}" }
    val lottie by lazy { "com.airbnb.android:lottie:${Versions.lottieVersion}" }
}

object Module {
    const val commons = ":commons"
    const val data = ":core:data"
    const val domain = ":core:domain"
}