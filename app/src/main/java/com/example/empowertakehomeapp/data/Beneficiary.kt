package com.example.empowertakehomeapp.data

import java.io.Serializable

data class Beneficiary(
    val lastName: String,
    val firstName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String,
    val phoneNumber: String,
    val beneficiaryAddress: Address
) : Serializable

