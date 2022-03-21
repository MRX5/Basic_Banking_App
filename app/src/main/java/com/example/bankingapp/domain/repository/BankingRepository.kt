package com.example.bankingapp.domain.repository

import com.example.bankingapp.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface BankingRepository {
    fun getCustomers(): Flow<List<Customer>>
    suspend fun getCustomerById(customerId:Int):Customer?
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
}