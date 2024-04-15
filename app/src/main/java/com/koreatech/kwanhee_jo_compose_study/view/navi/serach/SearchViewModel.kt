package com.koreatech.kwanhee_jo_compose_study.view.navi.serach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.core.network.repository.PohahangRepository
import com.koreatech.data.model.response.CatResponseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pohahangRepository: PohahangRepository,
) : ViewModel() {
    val _cats: MutableStateFlow<List<CatResponseDto>> = MutableStateFlow(emptyList())
    val cats = _cats.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _cats.emit(pohahangRepository.getCats())
            } catch (e: CancellationException) {

            }
        }
    }
}