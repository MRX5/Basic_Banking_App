package com.example.bankingapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.domain.use_case.GetCustomers
import com.example.bankingapp.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCustomersUseCase: GetCustomers
):ViewModel(){
    private var _customers=MutableStateFlow<List<Customer>>(emptyList())
    val customers: MutableStateFlow<List<Customer>> =_customers

    init {
        getCustomers()
    }

    private fun getCustomers() {
        viewModelScope.launch{
            getCustomersUseCase.invoke().onEach {
                _customers.value=it
            }.launchIn(viewModelScope)
        }
    }
}