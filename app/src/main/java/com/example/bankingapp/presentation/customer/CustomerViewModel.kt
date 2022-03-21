package com.example.bankingapp.presentation.customer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.use_case.GetCustomer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val getCustomerUseCase: GetCustomer) :
    ViewModel() {
    private var _customer = MutableStateFlow<Customer?>(null)
    val customer:MutableStateFlow<Customer?> =_customer

    fun getCustomer(id: Int) {
        viewModelScope.launch {
            getCustomerUseCase.invoke(id).also {
                _customer.value=it
            }
        }
    }
}