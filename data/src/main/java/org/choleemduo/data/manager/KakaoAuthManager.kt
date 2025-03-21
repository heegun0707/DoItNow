package org.choleemduo.data.manager

import android.content.Context
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import org.choleemduo.domain.manager.AuthState
import org.choleemduo.domain.manager.SocialNetwork
import timber.log.Timber
import javax.inject.Inject

class KakaoAuthManager @Inject constructor(
    private val userApiClient: UserApiClient,
    private val authApiClient: AuthApiClient,
    private val context: Context
) {
    private fun isSetOnKakaoTalk() = userApiClient.isKakaoTalkLoginAvailable(context)

    private fun hasToken() = authApiClient.hasToken()

    fun login(onResult: (AuthState) -> Unit) {
        if (isSetOnKakaoTalk()) {
            Timber.d("Kakaotalk app is installed on the phone.")
            loginWithKakaoTalk(onResult)
        } else {
            Timber.d("Kakaotalk app is uninstalled on the phone.")
            loginWithKakaoAccount(onResult)
        }
    }

    fun logout(onResult: (AuthState) -> Unit) {
        userApiClient.logout { error ->
            error?.let {
                Timber.e("Logout error: ${error.message}")
                onResult(AuthState.LogoutFail)
            } ?: run {
                Timber.i("Success logout")
                onResult(AuthState.LogoutSuccess)
            }
        }
    }

    @Suppress("LABEL_NAME_CLASH")
    private fun loginWithKakaoTalk(onResult: (AuthState) -> Unit) {
        userApiClient.loginWithKakaoTalk(context) { token, error ->
            error?.let {
                Timber.e("OAuthToken error: ${error.message}")
                onResult(AuthState.LoginFail)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
            } ?: token?.let {
                Timber.d("login success, token is ${token.accessToken}")
                onResult(AuthState.LoginSuccess(SocialNetwork.KAKAO))
            }
            loginWithKakaoAccount(onResult)
        }
    }

    private fun loginWithKakaoAccount(onResult: (AuthState) -> Unit) {
        userApiClient.loginWithKakaoAccount(context) { token, error ->
            error?.let {
                Timber.e("OAuthToken error: ${error.message}")
                onResult(AuthState.LoginFail)
            } ?: token?.let {
                Timber.d("login success, token is ${token.accessToken}")
                onResult(AuthState.LoginSuccess(SocialNetwork.KAKAO))
            }
        }
    }

    fun checkLoginRequired(onResult: (AuthState) -> Unit) {
        if (hasToken()) {
            userApiClient.accessTokenInfo { _, error ->
                error?.let {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        Timber.e("User is not logged in. Redirecting to the login screen.")
                    } else {
                        Timber.e("AccessTokenInfo error: ${error.message}")
                    }
                } ?: run {
                    Timber.d("Token validation successful. User can continue using the service.")
                    onResult(AuthState.LoginSuccess(SocialNetwork.KAKAO))
                }
            }
        } else {
            Timber.d("Invalid token. User needs to log in again.")
            onResult(AuthState.Idle)
        }
    }

    private fun getUserInfo() {
        userApiClient.me { user, error ->
            error?.let {
                Timber.e("User error: ${error.message}")
            } ?: user?.let {
                Timber.d(
                    "getUserInfo success," +
                            "\nUID: ${user.id}" +
                            "\nEmail: ${user.kakaoAccount?.email}" +
                            "\nNickname: ${user.kakaoAccount?.profile?.nickname}" +
                            "\nProfileImage: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
            }
        }
    }
}