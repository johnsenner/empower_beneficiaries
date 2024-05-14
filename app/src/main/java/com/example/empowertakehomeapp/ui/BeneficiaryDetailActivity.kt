package com.example.empowertakehomeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.empowertakehomeapp.R
import com.example.empowertakehomeapp.data.Address
import com.example.empowertakehomeapp.data.Beneficiary

class BeneficiaryDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val beneficiary = intent.getSerializableExtra("beneficiary") as Beneficiary

        setContent {
            MaterialTheme {
                BeneficiaryDetails(beneficiary)
            }
        }
    }

    @Composable
    fun BeneficiaryDetails(beneficiary: Beneficiary) {
        Column {
            Text(
                stringResource(R.string.name_label) + ": ${beneficiary.firstName} ${beneficiary.middleName} ${beneficiary.lastName}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.designation_label) + ": ${getDesignationDescription(beneficiary.designationCode)} - ${beneficiary.beneType}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                stringResource(R.string.ssn_label) + ": ${beneficiary.socialSecurityNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                stringResource(R.string.phone_label) + ": ${beneficiary.phoneNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                stringResource(R.string.dob_label) + ": ${formatDate(beneficiary.dateOfBirth)}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                stringResource(R.string.address_label) + ": ${formatAddress(beneficiary.beneficiaryAddress)}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    fun formatDate(date: String): String {
        return "${date.substring(0, 2)}/${date.substring(2, 4)}/${date.substring(4, 8)}"
    }

    fun formatAddress(address: Address): String {
        var addressString = "${address.firstLineMailing},\n"
        if (address.scndLineMailing != null) {
            addressString += "${address.scndLineMailing}\n"
        }
        addressString += "${address.city}, ${address.stateCode} ${address.zipCode}\n${address.country}"
        return addressString
    }

    fun getDesignationDescription(code: String): String {
        return when (code) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> code // Return the code itself if no match is found
        }
    }
}
