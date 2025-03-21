package org.choleemduo.domain.repository

import kotlinx.coroutines.flow.Flow
import org.choleemduo.domain.manager.AuthState
import org.choleemduo.domain.model.UserInfo

interface AuthenticationRepository {
    suspend fun loginWithKakao(): Flow<AuthState>
    suspend fun loginWithGoogle(): Flow<AuthState>
    suspend fun logoutWithKakao(): Flow<AuthState>
    suspend fun logoutWithGoogle(): Flow<AuthState>
    suspend fun checkLoginRequired(): Flow<AuthState>
    suspend fun getUserInfo(): Flow<UserInfo>
}