package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.dao.SuplierDao
import com.example.ucp2_pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySuplier (
    private val suplierDao: SuplierDao
) : RepositorySuplier {
    override suspend fun insertSuplier(suplier: Suplier){
        suplierDao.insertSuplier(suplier)
    }

    override suspend fun deleteSuplier(suplier: Suplier) {
        suplierDao.deleteSuplier(suplier)
    }

    override suspend fun updateSuplier(suplier: Suplier) {
        suplierDao.updateSuplier(suplier)
    }

    override fun getAllSuplier(): Flow<List<Suplier>> =
        suplierDao.getAllSuplier()
}