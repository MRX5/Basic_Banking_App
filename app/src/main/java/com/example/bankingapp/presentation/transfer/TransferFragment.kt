package com.example.bankingapp.presentation.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankingapp.R
import com.example.bankingapp.databinding.FragmentTransferBinding
import com.example.bankingapp.domain.model.Customer
import com.example.bankingapp.presentation.home.adapter.CustomerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TransferFragment : Fragment() {
    private lateinit var binding:FragmentTransferBinding
    private val viewModel:TransferViewModel by viewModels()
    private lateinit var currentCustomer:Customer
    private var amount:Int=0
    private val customerAdapter by lazy {
        CustomerAdapter{
            transferTo(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_transfer,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args=TransferFragmentArgs.fromBundle(it)
            amount=args.amount
            args.customer?.let { customer ->currentCustomer=customer  }
        }

        setupRecyclerView()
        observeCustomers()
    }

    private fun setupRecyclerView() {
        binding.customersRv.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=customerAdapter
        }
    }

    private fun observeCustomers() {
        lifecycleScope.launch {
            viewModel.customers.collect {
                val customersList=it.filter { s->s.id!=currentCustomer.id }
                customerAdapter.setData(customersList)
            }
        }
    }

    private fun transferTo(customer:Customer) {
        customer.balance = customer.balance?.plus(amount)
        viewModel.updateCustomer(customer)

        currentCustomer.balance=currentCustomer.balance?.minus(amount)
        viewModel.updateCustomer(currentCustomer)

        Toast.makeText(context,"Transfer Successful",Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }



}