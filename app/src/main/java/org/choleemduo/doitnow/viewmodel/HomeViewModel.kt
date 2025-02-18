package org.choleemduo.doitnow.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.choleemduo.doitnow.base.BaseViewModel
import org.choleemduo.domain.usecase.GetAdviceUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAdviceUseCase: GetAdviceUseCase
) : BaseViewModel() {
    private val _advice = MutableStateFlow("")
    val advice: StateFlow<String> = _advice

    fun getAdvice() {
        viewModelScope.launch {
            getAdviceUseCase()
                .catch {
                    Timber.e("error: getAdvice()")
                }.collect {
                    Timber.d("advice: ${it.advice} ")
                    _advice.value = it.advice
                }
        }
    }
}