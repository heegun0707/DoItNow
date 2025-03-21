package org.choleemduo.domain.manager


sealed class AuthState {
    data class LoginSuccess(val socialNetworkType: SocialNetwork): AuthState() {
        fun getType() = socialNetworkType
    }
    data object LoginFail: AuthState()
    data object LogoutSuccess: AuthState()
    data object LogoutFail: AuthState()
    data object Loading: AuthState()
    data object Idle: AuthState()
}