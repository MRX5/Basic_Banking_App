package com.example.bankingapp.domain.use_case

import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.repository.BankingRepository
import javax.inject.Inject

class UpdateCustomer @Inject constructor(private val repository: BankingRepository) {

    suspend operator fun invoke(customer: Customer) = repository.updateCustomer(customer)
}