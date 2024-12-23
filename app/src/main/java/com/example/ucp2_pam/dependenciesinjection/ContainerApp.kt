package com.example.ucp2_pam.dependenciesinjection


import com.example.ucp2_pam.repository.RepositoryBarang
import com.example.ucp2_pam.repository.RepositorySuplier

interface InterfaceContainerApp {
    val repositoryBarang: RepositoryBarang
    val repositorySuplier: RepositorySuplier
}