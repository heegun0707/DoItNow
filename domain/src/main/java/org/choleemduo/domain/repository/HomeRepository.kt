package org.choleemduo.domain.repository

import kotlinx.coroutines.flow.Flow
import org.choleemduo.domain.model.AdviceEntity

interface HomeRepository {
    suspend fun getAdvice(): Flow<AdviceEntity>
}