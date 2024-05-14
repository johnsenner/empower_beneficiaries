package com.example.empowertakehomeapp.data

import java.io.Serializable

data class Address(
    val firstLineMailing: String,
    val scndLineMailing: String?, // scndLineMailing is optional
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
) : Serializable