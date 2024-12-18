package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.dao.SuplierDao
import com.example.ucp2_pam.data.entity.Suplier

class LocalRepositorySp (
    private val suplierDao: SuplierDao
) : RepositorySp {
    override suspend fun insertSp(suplier: Suplier){
        suplierDao.insertSuplier(suplier)
    }

    override suspend fun deleteSp(suplier: Suplier) {
        suplierDao.deleteSuplier(suplier)
    }

    override suspend fun updateSp(suplier: Suplier) {
        suplierDao.updateSuplier(suplier)
    }
}