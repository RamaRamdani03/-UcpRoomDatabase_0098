package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map


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
}

data class HomeUiState (
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)