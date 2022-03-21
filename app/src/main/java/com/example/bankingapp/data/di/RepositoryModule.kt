package com.example.bankingapp.data.di

import com.example.bankingapp.data.local.BankingDao
import com.example.bankingapp.data.repository.BankingRepositoryImpl
import com.example.bankingapp.domain.repository.BankingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideBankingRepository(dao: BankingDao):BankingRepository= BankingRepositoryImpl(dao)

}