package org.choleemduo.doitnow.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.choleemduo.doitnow.base.BaseViewModel
import org.choleemduo.domain.manager.AuthState
import org.choleemduo.domain.manager.SocialNetwork
import org.choleemduo.domain.usecase.CheckLoginRequiredUseCase
import org.choleemduo.domain.usecase.LoginWithKakaoUseCase
import org.choleemduo.domain.usecase.LogoutWithKakaoUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginWithKakaoUseCase: LoginWithKakaoUseCase,
    private val logoutWithKakaoUseCase: LogoutWithKakaoUseCase,
    private val checkLoginRequiredUseCase: CheckLoginRequiredUseCase,
) : BaseViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState
    private var currentSocialNetworkType: SocialNetwork = SocialNetwork.NONE

    init {
        checkLoginRequired()
    }

    fun checkLoginRequired() {
        viewModelScope.launch {
            // TODO 최근에 로그인한 SNS 플랫폼 종류 앱 진입 시 어떻게 구분?
            when (currentSocialNetworkType) {
                SocialNetwork.KAKAO -> {
                    checkLoginRequiredUseCase()
                        .catch {
                            Timber.e("error: checkLoginRequired()")
                        }.collect { state ->
                            Timber.d("checkLoginRequired", "state: $state")
                            updateLoginState(state)
                        }
                }
                SocialNetwork.GOOGLE -> {
                    //TODO Google checkLoginRequired
                }
                else -> {
                    updateLoginState(AuthState.Idle)
                }
            }
        }
    }

    fun loginWithKakao() {
        viewModelScope.launch {
            loginWithKakaoUseCase()
                .catch {
                    Timber.e("error: loginWithKakao()")
                }.collect { state ->
                    Timber.d("state: $state")
                    updateLoginState(state)
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            when (currentSocialNetworkType) {
                SocialNetwork.KAKAO -> {
                    logoutWithKakaoUseCase()
                        .catch {
                            Timber.e("error: logoutWithKakao()")
                        }.collect { state ->
                            Timber.d("state: $state")
                            updateLoginState(state)
                        }
                }
                SocialNetwork.GOOGLE -> {
                    //TODO Google Logout
                }
                else -> {}
            }
        }
    }

    private fun updateLoginState(authState: AuthState) {
        _authState.tryEmit(authState)
    }

    fun setSocialNetworkType(type: SocialNetwork) {
        currentSocialNetworkType = type
    }
}