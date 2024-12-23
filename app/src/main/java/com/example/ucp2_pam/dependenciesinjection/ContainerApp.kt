package com.example.ucp2_pam.dependenciesinjection

import android.content.Context
import com.example.ucp2_pam.data.database.TokoDatabase
import com.example.ucp2_pam.repository.LocalRepositoryBarang
import com.example.ucp2_pam.repository.LocalRepositorySuplier
import com.example.ucp2_pam.repository.RepositoryBarang
import com.example.ucp2_pam.repository.RepositorySuplier

interface InterfaceContainerApp {
    val repositoryBarang: RepositoryBarang
    val repositorySuplier: RepositorySuplier
}

class ContainerApp (private val context: Context) : InterfaceContainerApp{
    override val repositoryBarang:RepositoryBarang by lazy {
        LocalRepositoryBarang(TokoDatabase.getDatabase(context).barangDao())
    }
    override val repositorySuplier:RepositorySuplier by lazy {
        LocalRepositorySuplier(TokoDatabase.getDatabase(context).supplierDao())
    }
}