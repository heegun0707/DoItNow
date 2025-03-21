package org.choleemduo.doitnow.di

import android.content.Context
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.choleemduo.data.manager.KakaoAuthManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserAuthModule {

    @Provides
    @Singleton
    fun provideKakaoUserApiClient(): UserApiClient {
        return UserApiClient.instance
    }

    @Provides
    @Singleton
    fun provideKakaoAuthApiClient(): AuthApiClient {
        return AuthApiClient.instance
    }

    @Provides
    @Singleton
    fun provideKakaoAuthManager(
        userApiClient: UserApiClient,
        authApiClient: AuthApiClient,
        @ApplicationContext context: Context
    ): KakaoAuthManager {
        return KakaoAuthManager(userApiClient, authApiClient, context)
    }
}