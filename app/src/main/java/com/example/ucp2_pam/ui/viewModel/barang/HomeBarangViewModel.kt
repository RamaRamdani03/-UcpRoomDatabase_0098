package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang

class HomeBarangViewModel (
    private val repositoryBarang: RepositoryBarang
) : ViewModel() {

}

data class HomeUiStateBarang (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)