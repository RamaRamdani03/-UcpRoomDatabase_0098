package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBarang {
    suspend fun insertBarang(barang: Barang)

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)

    fun getAllBarang(): Flow<List<Barang>>

    fun getBarang(id: String): Flow<Barang>
}