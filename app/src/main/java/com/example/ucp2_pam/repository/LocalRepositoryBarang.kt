package com.example.ucp2_pam.repository

import com.example.ucp2_pam.data.dao.BarangDao
import com.example.ucp2_pam.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBarang(
    private val barangDao: BarangDao
) : RepositoryBarang {
    override suspend fun insertBarang(barang: Barang){
        barangDao.insertBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang){
        barangDao.updateBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang){
        barangDao.deleteBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> =
        barangDao.getAllBarang()

    override fun getBarang(id: String): Flow<Barang> =
        barangDao.getBarang(id)
}