package com.example.empowertakehomeapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.empowertakehomeapp.BeneficiaryJSONReader
import com.example.empowertakehomeapp.R
import com.example.empowertakehomeapp.data.Address
import com.example.empowertakehomeapp.data.Beneficiary
import org.json.JSONArray

class BeneficiaryListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString = BeneficiaryJSONReader.read(context = this, "data.json")
        val beneficiaries = parseJson (jsonString)

        setContent {
            MaterialTheme {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(beneficiaries) { beneficiary ->
                        BeneficiaryListItem(beneficiary) {
                            // Create an intent to start BeneficiaryDetailActivity
                            val intent = Intent(this@BeneficiaryListActivity, BeneficiaryDetailActivity::class.java)
                            // Put the Beneficiary object as an extra
                            intent.putExtra("beneficiary", beneficiary)
                            // Start the BeneficiaryDetailActivity with the intent
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BeneficiaryListItem(beneficiary: Beneficiary, onClick: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(8.dp)

        ) {
            Text(
                stringResource(R.string.name_label) +": ${beneficiary.firstName} ${beneficiary.middleName} ${beneficiary.lastName}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(stringResource(R.string.type_label) +": ${beneficiary.beneType}", style = MaterialTheme.typography.bodyLarge)
        }
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
                    scndLineMailing = if (!address.isNull("scndLineMailing")) address.getString("scndLineMailing") else null,
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
