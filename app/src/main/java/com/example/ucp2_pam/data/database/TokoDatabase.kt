package com.example.ucp2_pam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_pam.data.dao.BarangDao
import com.example.ucp2_pam.data.dao.SuplierDao
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase(){

    abstract fun barangDao(): BarangDao

    abstract fun supplierDao(): SuplierDao

    companion object {
        @Volatile
        private var Instance: TokoDatabase? = null
    }
}