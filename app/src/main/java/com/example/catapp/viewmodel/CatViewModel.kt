package com.example.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CatModel
import com.example.domain.usecase.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val getCatsUseCase: GetCatsUseCase) : ViewModel() {

    private val _catState = MutableStateFlow<CatState>(CatState.Idle)
    val catState: StateFlow<CatState> = _catState

    fun getCats() = viewModelScope.launch {
        val response = getCatsUseCase(CAT_LIMIT, SKIP)
        _catState.value = response.fold(
            onSuccess = { cats ->
                CatState.Success(cats)
            },
            onFailure = {
                CatState.Error
            }
        )
    }

    sealed class CatState {
        object Idle : CatState()
        data class Success(val catList: List<CatModel>) : CatState()
        object Error : CatState()
    }

    companion object {
        private const val CAT_LIMIT = "10"
        private const val SKIP = "0"
    }

}