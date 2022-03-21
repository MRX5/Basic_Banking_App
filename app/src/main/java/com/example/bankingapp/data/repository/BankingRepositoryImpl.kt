package com.example.bankingapp.data.repository

import com.example.bankingapp.data.local.BankingDao
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.repository.BankingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BankingRepositoryImpl @Inject constructor(private val dao: BankingDao) : BankingRepository {

    override fun getCustomers(): Flow<List<Customer>> = dao.getCustomers()

    override suspend fun getCustomerById(customerId: Int): Customer? =dao.getCustomerById(customerId)

    override suspend fun insertCustomer(customer: Customer)=dao.insertCustomer(customer)

    override suspend fun updateCustomer(customer: Customer)=dao.updateCustomer(customer)

}