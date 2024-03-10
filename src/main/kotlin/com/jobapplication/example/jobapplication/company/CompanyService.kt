package com.jobapplication.example.jobapplication.company

interface CompanyService {

//    fun getAllCompanies(): List<Company>
    fun getAllCompanies(): List<CompanyDto>
    fun createCompany(company: Company)
    fun updateCompany(company: Company, id: String)
    fun deleteCompanyById(id: String):String
    fun getCompanyById(id: String): Company

}