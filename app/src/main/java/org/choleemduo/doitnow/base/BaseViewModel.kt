package org.choleemduo.doitnow.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel: ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _isLoading

    protected fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}