package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.repository.RepositoryBarang
import com.example.ucp2_pam.ui.navigasi.DestinasiDetailBarang

class DetailBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang,
) : ViewModel() {
    private val _id : String = checkNotNull(savedStateHandle[DestinasiDetailBarang.id])

}

data class DetailUiStateBarang(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEmpty: Boolean
        get() = detailUiEvent != BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() =detailUiEvent != BarangEvent()
}