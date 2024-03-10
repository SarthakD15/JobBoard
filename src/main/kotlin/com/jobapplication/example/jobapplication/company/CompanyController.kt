package com.jobapplication.example.jobapplication.company

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController(val companyService: CompanyService) {
//    @GetMapping("/allcompanies")
//    fun getAllCompanies() = companyService.getAllCompanies()

    @GetMapping("/allcompanies")
    fun getAllCompanies(): List<CompanyDto> = companyService.getAllCompanies()

    @PostMapping("/companies")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createCompany(@RequestBody company: Company)=companyService.createCompany(company)

    @PutMapping("/companies/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updateCompany(@RequestBody company: Company, @PathVariable id: String)=companyService.updateCompany(company,id)

    @DeleteMapping("/companies/remove/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    fun deleteCompany(@PathVariable id:String)=companyService.deleteCompanyById(id)

    @GetMapping("/allcompanies/{id}")
    @PreAuthorize("hasAuthority('ADMIN','USER')")
    fun getCompanyById(@PathVariable id: String)=companyService.getCompanyById(id)

}