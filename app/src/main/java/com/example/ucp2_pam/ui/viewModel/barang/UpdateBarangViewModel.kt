package com.example.ucp2_pam.ui.viewModel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang

class UpdateBarangViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang
) : ViewModel() {

    var updateUiStateBarang by mutableStateOf(BarangUiState())
        private set
}

fun Barang.toUiStateBarang() : BarangUiState = BarangUiState (
    barangEvent = this.toDetailUiEventBarang(),
)