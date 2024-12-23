package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier


class SuplierHomeViewModel (
    private val repositorySuplier: RepositorySuplier
) : ViewModel() {

}

data class HomeUiState (
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)