package org.choleemduo.doitnow.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import org.choleemduo.doitnow.R

// sealed class는 제한된 하위 클래스를 가지는 class
// 다른 곳에서 해당 클래스 상속 불가
// enum과 비슷한 성격이지만 enum은 서브 클래스 생성 불가(싱글톤 개념)
// 복잡한 상태나 결과는 sealed class, 간단한 상태나 옵션은 enum 느낌
sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val iconId: Int
) {
    data object Login: Screen(
        route = "login",
        resourceId = R.string.navigation_login,
        iconId = 0
    )

    data object Manage: Screen(
        route = "manage",
        resourceId = R.string.navigation_manage,
        iconId = R.drawable.icon_manage
    )

    data object Search: Screen(
        route = "search",
        resourceId = R.string.navigation_search,
        iconId = R.drawable.icon_search
    )

    data object Collection: Screen(
        route = "collection",
        resourceId = R.string.navigation_collection,
        iconId = R.drawable.icon_collection
    )

    data object Add: Screen(
        route = "add",
        resourceId = R.string.navigation_add,
        iconId = R.drawable.icon_add
    )

    data object Alarm: Screen(
        route = "alarm",
        resourceId = R.string.navigation_alarm,
        iconId = R.drawable.icon_alarm
    )

    data object Setting: Screen(
        route = "setting",
        resourceId = R.string.navigation_setting,
        iconId = R.drawable.icon_settings
    )
}