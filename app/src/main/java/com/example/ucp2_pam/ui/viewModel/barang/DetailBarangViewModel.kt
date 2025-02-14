package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang
import com.example.ucp2_pam.ui.navigasi.DestinasiDetailBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang,
) : ViewModel() {
    private val _id : String = checkNotNull(savedStateHandle[DestinasiDetailBarang.id])

    val detailUiState: StateFlow<DetailUiStateBarang> = repositoryBarang.getBarang(_id)
        .filterNotNull()
        .map {
            DetailUiStateBarang (
                detailUiEvent = it.toDetailUiEventBarang(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiStateBarang(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiStateBarang(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiStateBarang(
                isLoading = true,
            ),
        )

    fun deleteBarang() {
        detailUiState.value.detailUiEvent.toBarangEntity(). let {
            viewModelScope.launch {
                repositoryBarang.deleteBarang(it)
            }
        }
    }
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

fun Barang.toDetailUiEventBarang () : BarangEvent{
    return BarangEvent(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        NamaSuplier = NamaSuplier
    )
}