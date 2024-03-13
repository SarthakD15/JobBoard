package com.jobapplication.example.jobapplication.company.Impl

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyDto
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.company.CompanyService
import com.jobapplication.example.jobapplication.security.CustomUserDetailsService
import com.jobapplication.example.jobapplication.security.UserEntity
import com.jobapplication.example.jobapplication.security.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*


@Service
class CompanyServiceImpl(var companyRepository: CompanyRepository,var userRepository: UserRepository, var customUserDetailsService: CustomUserDetailsService):CompanyService{
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

    override fun createCompany(company: Company) : ResponseEntity<String>{
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}
        println(completeuser)
        if(completeuser.company!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already has a company. Cannot create another.")
        }
        company.id= UUID.randomUUID().toString().replace("-","").substring(0,12)
//        company.id= UUID.randomUUID().toString().replace("-","").substring(0,8)
        userRepository.save(completeuser)
        companyRepository.save(company)

        completeuser.company = company
        company.userEntity = completeuser

        companyRepository.save(company)
        userRepository.save(completeuser)
        return ResponseEntity.status(HttpStatus.OK).body("Company created")
    }

    override fun updateCompany(company: Company, id: String):ResponseEntity<String> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}

        var companies : List<Company> = companyRepository.findByCompany(id)
        for (it in companies){
            var userid = it.userEntity?.id
            if(completeuser.id != userid){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not the admin of this company")
            }

        }
        val oldCompany = companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
        oldCompany.title=company.title
        oldCompany.description=company.description
        companyRepository.save(oldCompany)

        return ResponseEntity.status(HttpStatus.OK).body("Company updated")
    }

    override fun deleteCompanyById(id: String): ResponseEntity<String> {
        val oldCompany = companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
        companyRepository.delete(oldCompany)

        return ResponseEntity.status(HttpStatus.OK).body("Company deletd")
    }

    override fun getCompanyById(id: String): Company {
        return companyRepository.findById(id).orElseThrow{RuntimeException("Company not found")}
    }

}