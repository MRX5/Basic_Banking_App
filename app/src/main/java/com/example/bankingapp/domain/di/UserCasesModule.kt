package com.example.bankingapp.domain.di

import com.example.bankingapp.domain.repository.BankingRepository
import com.example.bankingapp.domain.use_case.GetCustomer
import com.example.bankingapp.domain.use_case.GetCustomers
import com.example.bankingapp.domain.use_case.InsertCustomer
import com.example.bankingapp.domain.use_case.UpdateCustomer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UserCasesModule {

    @ViewModelScoped
    @Provides
    fun provideGetCustomersUseCase(repository: BankingRepository):GetCustomers= GetCustomers(repository)

    @ViewModelScoped
    @Provides
    fun provideGetCustomerUseCase(repository: BankingRepository):GetCustomer= GetCustomer(repository)

    @ViewModelScoped
    @Provides
    fun provideUpdateCustomerUseCase(repository: BankingRepository):UpdateCustomer= UpdateCustomer(repository)

    @ViewModelScoped
    @Provides
    fun provideInsertCustomerUseCase(repository: BankingRepository):InsertCustomer= InsertCustomer(repository)
}