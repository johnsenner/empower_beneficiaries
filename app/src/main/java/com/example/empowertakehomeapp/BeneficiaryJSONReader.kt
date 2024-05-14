package com.example.empowertakehomeapp

import android.content.Context

class BeneficiaryJSONReader {
    // companion object to hold static method
    companion object {
        // Reads a json file, given context and filename
        fun read(context: Context, fileName: String): String {
            context.assets.open(fileName).bufferedReader().use {
                return it.readText()
            }
        }
    }
}
