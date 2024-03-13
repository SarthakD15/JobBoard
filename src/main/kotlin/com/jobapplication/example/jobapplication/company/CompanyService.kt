package com.jobapplication.example.jobapplication.company

import org.springframework.http.ResponseEntity

interface CompanyService {

//    fun getAllCompanies(): List<Company>
    fun getAllCompanies(): List<CompanyDto>
    fun createCompany(company: Company) : ResponseEntity<String>
    fun updateCompany(company: Company, id: String):ResponseEntity<String>
    fun deleteCompanyById(id: String):ResponseEntity<String>
    fun getCompanyById(id: String): Company

}