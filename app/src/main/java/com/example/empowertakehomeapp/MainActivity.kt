package com.example.empowertakehomeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString = BeneficiaryJSONReader.read(context = this, "data.json");
        val beneficiaries = parseJson(jsonString)
    }

    private fun parseJson(jsonString: String): List<Beneficiary> {
        val jsonArray = JSONArray(jsonString)
        val beneficiaries = mutableListOf<Beneficiary>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val address = jsonObject.getJSONObject("beneficiaryAddress")
            val beneficiary = Beneficiary(
                lastName = jsonObject.getString("lastName"),
                firstName = jsonObject.getString("firstName"),
                designationCode = jsonObject.getString("designationCode"),
                beneType = jsonObject.getString("beneType"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                middleName = jsonObject.getString("middleName"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = Address(
                    firstLineMailing = address.getString("firstLineMailing"),
                    // scndLineMailing is optional, default "" to prevent
                    scndLineMailing = if (!address.isNull("scndLineMailing")) address.getString("scndLineMailing") else "",
                    city = address.getString("city"),
                    zipCode = address.getString("zipCode"),
                    stateCode = address.getString("stateCode"),
                    country = address.getString("country")
                )
            )
            beneficiaries.add(beneficiary)
        }
        return beneficiaries
    }
}

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
)

data class Address(
    val firstLineMailing: String,
    val scndLineMailing: String?, // scndLineMailing is optional
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
)
