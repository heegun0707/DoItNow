package org.choleemduo.domain.usecase

import org.choleemduo.domain.repository.HomeRepository
import javax.inject.Inject

class GetAdviceUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke() = repository.getAdvice()
}