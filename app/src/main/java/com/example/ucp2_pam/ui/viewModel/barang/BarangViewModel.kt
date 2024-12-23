package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.repository.RepositoryBarang

class BarangViewModel (private val repositoryBarang: RepositoryBarang) : ViewModel() {

}

data class FormErrorStateBarang(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val NamaSuplier: String? = null,
) {
    fun isValid(): Boolean{
        return id == null && nama == null && deskripsi == null && harga == null &&
                stok == null && NamaSuplier == null
    }
}

data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val NamaSuplier: String = "",
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    NamaSuplier = NamaSuplier,
)