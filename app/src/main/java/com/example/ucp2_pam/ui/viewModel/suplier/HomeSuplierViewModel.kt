package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


class SuplierHomeViewModel (
    private val repositorySuplier: RepositorySuplier
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = repositorySuplier.getAllSuplier()
        .filterNotNull()
        .map {
            HomeUiState (
                listSuplier = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
}

data class HomeUiState (
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)