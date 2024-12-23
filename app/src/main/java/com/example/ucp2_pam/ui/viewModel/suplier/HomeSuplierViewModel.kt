package com.example.ucp2_pam.ui.viewModel.suplier

import com.example.ucp2_pam.data.entity.Suplier


data class HomeUiState (
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)