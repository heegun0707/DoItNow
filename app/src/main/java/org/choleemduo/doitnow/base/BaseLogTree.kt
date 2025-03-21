package org.choleemduo.doitnow.base

import timber.log.Timber

class BaseLogTree(private val appTag: String): Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return appTag
    }
}