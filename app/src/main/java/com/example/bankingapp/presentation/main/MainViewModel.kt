package com.example.bankingapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.use_case.InsertCustomer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val insertCustomerUseCase: InsertCustomer) :ViewModel() {

    fun insertCustomer(customer: Customer){
        viewModelScope.launch {
            insertCustomerUseCase.invoke(customer)
        }
    }
}