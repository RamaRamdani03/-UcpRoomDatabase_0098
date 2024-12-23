package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeBarangViewModel (
    private val repositoryBarang: RepositoryBarang
) : ViewModel() {

    val homeUiStateBarang: StateFlow<HomeUiStateBarang> = repositoryBarang.getAllBarang()
        .filterNotNull()
        .map {
            HomeUiStateBarang (
                listBarang = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiStateBarang(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateBarang(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateBarang(
                isError = true
            )
        )
}

data class HomeUiStateBarang (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)