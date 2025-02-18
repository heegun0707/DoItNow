package org.choleemduo.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.choleemduo.domain.model.AdviceEntity
import org.choleemduo.domain.repository.HomeRepository
import javax.inject.Inject
import kotlin.random.Random

class HomeRepositoryImpl @Inject constructor(): HomeRepository {

    // if you emit a single data or static data : flowOf()
//    override suspend fun getAdvice(): Flow<AdviceEntity> {
//        val advice = AdviceEntity.createDummyData()
//        return flowOf(advice)
//    }

    // if you emit a multiple data or sequential data : flow { }
    override suspend fun getAdvice(): Flow<AdviceEntity> = flow {
        val adviceList = AdviceEntity.createDummyData()
        emit(adviceList[Random.nextInt(adviceList.size)])
    }
}