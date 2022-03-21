package com.example.bankingapp.domain.use_case

import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.repository.BankingRepository
import javax.inject.Inject

class InsertCustomer @Inject constructor(private val repository: BankingRepository){

    suspend operator fun invoke(customer: Customer)=repository.insertCustomer(customer)
}