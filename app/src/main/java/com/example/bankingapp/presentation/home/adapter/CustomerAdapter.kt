package com.example.bankingapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.databinding.CustomerLayoutBinding
import com.example.bankingapp.domain.model.Customer
import java.util.zip.Inflater

class CustomerAdapter(private val click:(customer: Customer)->Unit): RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    private var customers= mutableListOf<Customer>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomerViewHolder(
        CustomerLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(customers[position])
    }

    override fun getItemCount()=customers.size

    fun setData(customers:List<Customer>){
        if(customers.isNotEmpty()) {
            this.customers = customers as MutableList<Customer>
            notifyDataSetChanged()
        }
    }

    inner class CustomerViewHolder(private val binding: CustomerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: Customer) = with(binding) {
            this.customer = customer
            binding.root.setOnClickListener {
                click(customer)
            }
        }
    }
}