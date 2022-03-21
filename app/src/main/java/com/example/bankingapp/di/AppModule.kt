package com.example.bankingapp.di

import android.content.Context
import androidx.room.Room
import com.example.bankingapp.data.local.BankingDao
import com.example.bankingapp.data.local.BankingDatabase
import com.example.bankingapp.data.repository.BankingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBankingDatabase(@ApplicationContext context: Context) :BankingDatabase=
        Room.databaseBuilder(context, BankingDatabase::class.java, BankingDatabase.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBankingDao(database: BankingDatabase):BankingDao=database.dao()

}