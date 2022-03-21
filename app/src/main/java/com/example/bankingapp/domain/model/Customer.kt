package com.example.bankingapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var name:String,
    var email:String,
    var balance:Int?
): Parcelable
