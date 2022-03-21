package com.example.bankingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bankingapp.domain.model.Customer

@Database(entities = [Customer::class],version = 1)
abstract class BankingDatabase :RoomDatabase(){
    abstract fun dao():BankingDao
    companion object{
        const val DATABASE_NAME="banking_db"
    }
}