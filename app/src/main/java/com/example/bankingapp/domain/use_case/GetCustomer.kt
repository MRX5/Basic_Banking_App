package com.example.bankingapp.domain.use_case

import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.repository.BankingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCustomer @Inject constructor(private val bankingRepository: BankingRepository){

     suspend operator fun invoke(id:Int): Customer? =bankingRepository.getCustomerById(id)
}