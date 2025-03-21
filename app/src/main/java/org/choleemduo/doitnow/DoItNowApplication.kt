package org.choleemduo.doitnow

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import org.choleemduo.doitnow.base.BaseLogTree
import timber.log.Timber

@HiltAndroidApp
class DoItNowApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(BaseLogTree("DoItNow"))

        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}