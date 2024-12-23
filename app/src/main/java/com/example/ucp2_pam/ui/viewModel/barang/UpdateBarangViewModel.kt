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

    fun validateFields() : Boolean {
        val event = updateUiStateBarang.barangEvent
        val errorStateBarang = FormErrorStateBarang(
            id = if (event.id.isNotEmpty()) null else "Id Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Tidak Boleh Kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Tidak Boleh Kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            NamaSuplier = if (event.NamaSuplier.isNotEmpty()) null else "Nama Suplier Tidak Boleh Kosong",
        )

        updateUiStateBarang = updateUiStateBarang.copy(isEntryValid = errorStateBarang)
        return errorStateBarang.isValid()
    }

    fun updateData() {
        val currentEvent = updateUiStateBarang.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBarang.updateBarang(currentEvent.toBarangEntity())
                    updateUiStateBarang = updateUiStateBarang.copy(
                        snackBarMessage = "Data Berhasil di Update",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorStateBarang()
                    )
                    println("SnackBarMessageDiatur: ${updateUiStateBarang.snackBarMessage}")
                } catch (e: Exception) {
                    updateUiStateBarang = updateUiStateBarang.copy(
                        snackBarMessage = "Data Gagal diUpate"
                    )
                }
            }
        } else {
            updateUiStateBarang = updateUiStateBarang.copy(
                snackBarMessage = "Data Gagal diUpdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUiStateBarang = updateUiStateBarang.copy(snackBarMessage = null)
    }
}

fun Barang.toUiStateBarang() : BarangUiState = BarangUiState (
    barangEvent = this.toDetailUiEventBarang(),
)