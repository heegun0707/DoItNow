package org.choleemduo.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.choleemduo.data.manager.KakaoAuthManager
import org.choleemduo.domain.manager.AuthState
import org.choleemduo.domain.model.UserInfo
import org.choleemduo.domain.repository.AuthenticationRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepositoryImpl @Inject constructor(
    private val kakaoAuthManager: KakaoAuthManager
): AuthenticationRepository {

    override suspend fun loginWithKakao(): Flow<AuthState> = flow {
        // suspendCoroutine 함수는 콜백을 사용하여 비동기 작업을 수행할 수 있게 해줍니다.
        // 콜백이 호출되면 continuation.resume을 통해 결과를 반환합니다.
        // 한 마디로 콜백 함수가 코루틴 동작을 할 수 있도록 해줌
        val state = suspendCoroutine { continuation ->
            kakaoAuthManager.login { result ->
                continuation.resume(result)
            }
        }
        emit(state)
    }

    override suspend fun loginWithGoogle(): Flow<AuthState> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun logoutWithKakao(): Flow<AuthState> = flow {
        val state = suspendCoroutine { continuation ->
            kakaoAuthManager.logout { result ->
                continuation.resume(result)
            }
        }
        emit(state)
    }

    override suspend fun logoutWithGoogle(): Flow<AuthState> {
        TODO("Not yet implemented")
    }

    override suspend fun checkLoginRequired(): Flow<AuthState> = flow {
        val state = suspendCoroutine { continuation ->
            kakaoAuthManager.checkLoginRequired { result ->
                continuation.resume(result)
            }
        }
        emit(state)
    }

    override suspend fun getUserInfo(): Flow<UserInfo> {
        TODO("Not yet implemented")
    }
}