package com.mokelab.demo.feature.mokera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.demo.core.data.MokeraRepository
import com.mokelab.demo.core.model.Mokera
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MokeraListViewModel @Inject constructor(
    private val repository: MokeraRepository,
) : ViewModel() {
    sealed interface UiState {
        data object Initial : UiState
        data object Loading : UiState
        data class Success(val items: List<Mokera>) : UiState
        data class Error(val th: Throwable) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun load() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val items = repository.getAll()
                _uiState.value = UiState.Success(items)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
}