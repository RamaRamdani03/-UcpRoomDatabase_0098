package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.entity.Suplier
interface RepositorySuplier {
    suspend fun insertSuplier(suplier: Suplier)

    suspend fun deleteSuplier(suplier: Suplier)

    suspend fun updateSuplier(suplier: Suplier)
}