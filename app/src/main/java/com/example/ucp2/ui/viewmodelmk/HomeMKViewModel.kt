package com.example.ucp2.ui.viewmodelmk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeMatkulViewModel(
    private val repositoryMK: RepositoryMK
) : ViewModel() {
    val homeUiState: StateFlow<HomeMatkulUiState> = repositoryMK.getAllMk()
        .filterNotNull()
        .map {
            HomeMatkulUiState(
                listMatkul = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeMatkulUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeMatkulUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMatkulUiState(
                isLoading = true,
            )
        )
}

data class HomeMatkulUiState(
    val listMatkul: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
