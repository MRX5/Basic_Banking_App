package com.example.bankingapp.presentation.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.bankingapp.R
import com.example.bankingapp.databinding.FragmentCustomerBinding
import com.example.bankingapp.domain.model.Customer
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBinding
    private val viewModel by viewModels<CustomerViewModel>()
    private lateinit var customer: Customer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            CustomerFragmentArgs.fromBundle(it).customer?.let { customer->
                    this.customer=customer
            }
        }

        fetchCustomer()
        binding.transferButton.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
        val editText = dialogView.findViewById<EditText>(R.id.amount_edittext)
        dialogView.findViewById<MaterialButton>(R.id.continue_btn).setOnClickListener {
            if (editText.text.isNotEmpty()) {
                customer.balance?.let {current_balance->
                    val amount = editText.text.toString().toInt()
                    if (amount > current_balance) editText.error = "You don't have enough balance"
                    else {
                        val directions=CustomerFragmentDirections.actionCustomerFragmentToTransferFragment(amount,customer)
                        requireView().findNavController().navigate(directions)
                        dialog.dismiss()
                    }
                }
            }
        }

        dialogView.findViewById<MaterialButton>(R.id.cancel_btn).setOnClickListener {
            dialog.dismiss()
        }
    }


    private fun fetchCustomer() {
        customer.id?.let {
            viewModel.getCustomer(it)
        }

        lifecycleScope.launch {
            viewModel.customer.collect {
                it?.let { current_customer ->
                    customer = current_customer
                    binding.customerName.text = customer.name
                    binding.customerEmail.text = customer.email
                    binding.customerBalance.text = customer.balance.toString().plus(" $")
                }
            }
        }
    }


}