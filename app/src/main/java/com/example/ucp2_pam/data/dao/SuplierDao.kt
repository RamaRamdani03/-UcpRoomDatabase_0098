package com.example.ucp2_pam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.ucp2_pam.data.entity.Suplier

@Dao
interface SuplierDao{
    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Update
    suspend fun updateSuplier(suplier: Suplier)

    @Delete
    suspend fun deleteSuplier(suplier: Suplier)
}