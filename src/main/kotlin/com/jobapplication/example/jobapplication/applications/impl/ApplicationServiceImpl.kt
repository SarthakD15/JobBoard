package com.jobapplication.example.jobapplication.applications.impl

import com.jobapplication.example.jobapplication.applications.Application
import com.jobapplication.example.jobapplication.applications.ApplicationDto
import com.jobapplication.example.jobapplication.applications.ApplicationRepository
import com.jobapplication.example.jobapplication.applications.ApplicationService
import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.email.EmailDetails
import com.jobapplication.example.jobapplication.email.EmailService
import com.jobapplication.example.jobapplication.security.UserEntity
import com.jobapplication.example.jobapplication.security.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplicationServiceImpl(var applicationRepository: ApplicationRepository, var userRepository: UserRepository, var companyRepository: CompanyRepository,var emailService: EmailService):ApplicationService {
    override fun getAllApplications(cid:String, jid:String): ResponseEntity<List<ApplicationDto>> {
        var companypassed : Company = companyRepository.findById(cid).orElseThrow{RuntimeException("Company Not found")}
        var userID = companypassed.userEntity?.id
        println(userID)

        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}
        println(completeuser.id)
        if(userID == completeuser.id) {
            return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.findAll().filter { it.job?.company?.id == cid && it.job?.id == jid }.map { it.toDTO() })
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    override fun createApplication(cid: String,id:String, application: Application) :ResponseEntity<String> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}

        application.id= UUID.randomUUID().toString()
        applicationRepository.save(application)

        val emailDetails = EmailDetails(completeuser.email, "Thank you for applying", "We have received your application")
        emailService.sendSimpleMail(emailDetails)
        return ResponseEntity.status(HttpStatus.OK).body("Application sent")
    }

    override fun updateApplication(cid: String, jid: String, aid: String, application: Application) {
        val oldApplication = applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
        oldApplication.name=application.name
        oldApplication.edu=application.edu
        oldApplication.coverLetter=application.coverLetter
        applicationRepository.save(oldApplication)
    }

    override fun deleteApplicationById(cid: String, jid: String, aid:String): String {
        val oldApplication = applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
        applicationRepository.delete(oldApplication)
        return "Application Deleted"
    }

    override fun getApplicationById(cid: String, jid: String, aid:String): Application {
        return applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
    }

    override fun acceptApplication(cid: String, jid: String, aid: String): String {
        val oldApplication = applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
        oldApplication.status=true
        applicationRepository.save(oldApplication)

        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}
        println(completeuser)

        val emailDetails = EmailDetails(completeuser.email, "Application approved", "Congrats")
        emailService.sendSimpleMail(emailDetails)
        return "Application Accepted"
    }

    override fun rejectApplication(cid: String, jid: String, aid: String): String {
        val oldApplication = applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
        oldApplication.status=false
        applicationRepository.save(oldApplication)
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}
        println(completeuser)

        val emailDetails = EmailDetails(completeuser.email, "Application rejected", "Sorry")
        emailService.sendSimpleMail(emailDetails)
        return "Application Rejected"
    }

//    override fun getApplicationsforuser(id:String,cid: String,jid: String): List<ApplicationDto>{
//
//        val user = userRepository.findById(id.toInt()).orElseThrow {EntityNotFoundException("User not found") }
//
//        val company = user.company ?: throw EntityNotFoundException("Company not found for the user")
//
//        val applications = company.jobs.
//
//        return applications.map { it.toDTO() }
//    }
}
