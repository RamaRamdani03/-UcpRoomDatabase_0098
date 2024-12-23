package com.example.ucp2_pam.ui.viewModel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang
import com.example.ucp2_pam.ui.navigasi.DestinasiUpdateBarang
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBarangViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang
) : ViewModel() {

    var updateUiStateBarang by mutableStateOf(BarangUiState())
        private set

    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBarang.id])

    init {
        viewModelScope.launch {
            updateUiStateBarang = repositoryBarang.getBarang(_id)
                .filterNotNull()
                .first()
                .toUiStateBarang()
        }
    }


    fun updateState(barangEvent: BarangEvent) {
        updateUiStateBarang = updateUiStateBarang.copy(
            barangEvent = barangEvent,
        )
    }
}

fun Barang.toUiStateBarang() : BarangUiState = BarangUiState (
    barangEvent = this.toDetailUiEventBarang(),
)