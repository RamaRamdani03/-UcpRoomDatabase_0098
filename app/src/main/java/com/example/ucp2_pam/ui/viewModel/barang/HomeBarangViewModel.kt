package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

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
}

data class HomeUiStateBarang (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)