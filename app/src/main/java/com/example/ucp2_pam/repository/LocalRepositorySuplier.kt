package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.dao.SuplierDao
import com.example.ucp2_pam.data.entity.Suplier

class LocalRepositorySuplier (
    private val suplierDao: SuplierDao
) : RepositorySuplier {
    override suspend fun insertSuplier(suplier: Suplier){
        suplierDao.insertSuplier(suplier)
    }
}