Beneficiaries Android App
Overview
The Beneficiaries Android app provides information on an account holder's beneficiaries. The app displays a list of beneficiaries using data from a JSON file and shows detailed information upon selecting a beneficiary. The app adheres to best practices with proper separation of concerns and utilizes Jetpack Compose for the UI.

Requirements Fulfilled
Display a list of beneficiaries using data from the attached JSON file:

Each row displays the firstName, lastName, beneType, and designation.
A "P" designationCode translates to "Primary" and a "C" designationCode translates to "Contingent".
Show additional information upon selecting a beneficiary:

Display SSN, date of birth, phone number, and address.
Date of birth is formatted as "MM/dd/yyyy".

Well-constructed, easy to follow, commented code:

Proper separation of concerns and best-practice coding patterns.
Deserialization of model objects from JSON.

Project Structure

Data Classes (Model)
The Beneficiary and Address data classes represent the structure of the JSON data. These classes implement Serializable to allow passing data between activities.

Repository
The BeneficiaryRepository class handles data retrieval and parsing from the JSON file stored in the assets.

MainActivity
The MainActivity displays a list of beneficiaries using a LazyColumn in Jetpack Compose. Each item in the list is clickable, leading to the BeneficiaryDetailActivity with detailed information.

BeneficiaryDetailActivity
The BeneficiaryDetailActivity displays detailed information about the selected beneficiary using Jetpack Compose.

Thank you!
John Senner
john.senner@gmail.com
