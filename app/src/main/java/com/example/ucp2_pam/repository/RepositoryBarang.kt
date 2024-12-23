package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.entity.Barang

interface RepositoryBarang {
    suspend fun insertBarang(barang: Barang)

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}