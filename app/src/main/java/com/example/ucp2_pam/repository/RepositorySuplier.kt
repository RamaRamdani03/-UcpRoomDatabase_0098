package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySuplier {
    suspend fun insertSuplier(suplier: Suplier)

    suspend fun deleteSuplier(suplier: Suplier)

    suspend fun updateSuplier(suplier: Suplier)

    fun getAllSuplier(): Flow<List<Suplier>>

    fun getSuplier(id: String): Flow<Suplier>
}