package com.example.ucp2_pam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase(){

}