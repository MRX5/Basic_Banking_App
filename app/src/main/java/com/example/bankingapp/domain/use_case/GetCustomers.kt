package com.example.bankingapp.domain.use_case

import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.repository.BankingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCustomers @Inject constructor(private val repository: BankingRepository){

    operator fun invoke(): Flow<List<Customer>> =repository.getCustomers()
}