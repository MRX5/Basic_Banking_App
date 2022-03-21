package com.example.bankingapp.data.local

import androidx.room.*
import com.example.bankingapp.domain.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface BankingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer:Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("SELECT * FROM customer where id = :customerId")
     suspend fun getCustomerById(customerId: Int):Customer?

    @Query("SELECT * FROM customer")
    fun getCustomers():Flow<List<Customer>>
}