package com.jobapplication.example.jobapplication.company.Impl

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyDto
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.company.CompanyService
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class CompanyServiceImpl(var companyRepository: CompanyRepository):CompanyService{
    override fun getAllCompanies(): List<CompanyDto> {
        return companyRepository.findAll().map { it.toDTO() }
    }

//    override fun getAllCompanies(): List<Company>{
//        return companyRepository.findAll()
//    }
//    fun convertEntityTODto(company: Company) : CompanyDto? {
//        var companyDto: CompanyDto?=null
//        companyDto?.id= company.id.toString()
//        companyDto?.title=company.id.toString()
//        companyDto?.description=company.id.toString()
//        companyDto?.jobs=company.jobs
//        return companyDto
//    }

    override fun createCompany(company: Company) {
        company.id= UUID.randomUUID().toString()
        companyRepository.save(company)
    }

    override fun updateCompany(company: Company, id: String) {
        val oldCompany = companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
        oldCompany.title=company.title
        oldCompany.description=company.description
        companyRepository.save(oldCompany)
    }

    override fun deleteCompanyById(id: String): String {
        val oldCompany = companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
        companyRepository.delete(oldCompany)
        return "company deleted"
    }

    override fun getCompanyById(id: String): Company {
        return companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
    }

}