package com.example.ucp2_pam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.ucp2_pam.data.entity.Barang

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)

    @Update
    suspend fun updateBarang(barang: Barang)

    @Delete
    suspend fun deleteBarang(barang: Barang)
}