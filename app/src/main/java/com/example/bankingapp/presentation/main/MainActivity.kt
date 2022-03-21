package com.example.bankingapp.presentation.main

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.bankingapp.R
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.presentation.utils.Constants
import com.example.bankingapp.presentation.utils.Constants.FIRST_TIME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener(this)

        sp = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        val firstTime = sp.getBoolean(FIRST_TIME, true)
        if (firstTime) {
            createDummyData()
        }
    }

    private fun createDummyData() {
        viewModel.insertCustomer(Customer(1, "Mostafa Gad", "mostafa@gmail.com", 1000))
        viewModel.insertCustomer(Customer(2, "Asmaa Sobhy", "asmaa@gmail.com", 10000))
        viewModel.insertCustomer(Customer(3, "Ahmed Ali", "ali@gmail.com", 3000))
        viewModel.insertCustomer(Customer(4, "Mostafa Ibrahim", "ibrahim@gmail.com", 4000))
        viewModel.insertCustomer(Customer(5, "Sarah Ahmed", "sarah@gmail.com", 5000))
        viewModel.insertCustomer(Customer(6, "Murad Murad", "murad@gmail.com", 6000))
        viewModel.insertCustomer(Customer(7, "Mohamed Abdo", "mohamed@gmail.com", 7000))
        viewModel.insertCustomer(Customer(8, "Sohib Gamal", "sohib@gmail.com", 8000))
        viewModel.insertCustomer(Customer(9, "Ahmed Emam", "emam@gmail.com", 9000))
        viewModel.insertCustomer(Customer(10, "Shaimaa Gad", "shaimaa@gmail.com", 2000))

        val editor = sp.edit()
        editor.putBoolean(FIRST_TIME, false)
        editor.apply()

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        supportActionBar?.title = destination.label
        if(destination.id==R.id.homeFragment){
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }else{
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}