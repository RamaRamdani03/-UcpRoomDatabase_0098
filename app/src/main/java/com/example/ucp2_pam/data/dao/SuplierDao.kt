package com.example.ucp2_pam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2_pam.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao{
    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Update
    suspend fun updateSuplier(suplier: Suplier)

    @Delete
    suspend fun deleteSuplier(suplier: Suplier)

    @Query("SELECT * FROM suplier ORDER BY nama ASC")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM suplier WHERE id = :id")
    fun getSuplier(id: String): Flow<Suplier>
}