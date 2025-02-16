package org.choleemduo.domain.model

data class AdviceEntity(
    val id: Int,
    val advice: String
) {
    companion object {
        fun createDummyData(): List<AdviceEntity> {
            val list = ArrayList<AdviceEntity>()
            for (i in 1..10) {
                list.add(
                    AdviceEntity(
                        id = i,
                        advice = "i want to ${"really ".repeat(i)}go home"
                    )
                )
            }
            return list
        }
    }
}
