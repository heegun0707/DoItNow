package org.choleemduo.domain.usecase

import org.choleemduo.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogoutWithKakaoUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() = repository.logoutWithKakao()
}