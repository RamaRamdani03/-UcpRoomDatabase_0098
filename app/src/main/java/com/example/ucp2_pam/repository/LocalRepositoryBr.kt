package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.dao.BarangDao
import com.example.ucp2_pam.data.entity.Barang

class LocalRepositoryBr(
    private val barangDao: BarangDao
) : RepositoryBr{
    override suspend fun insertBr(barang: Barang){
        barangDao.insertBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang){
        barangDao.updateBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang){
        barangDao.deleteBarang(barang)
    }
}