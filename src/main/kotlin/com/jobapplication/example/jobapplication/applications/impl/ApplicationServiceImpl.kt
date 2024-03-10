package com.jobapplication.example.jobapplication.applications.impl

import com.jobapplication.example.jobapplication.applications.Application
import com.jobapplication.example.jobapplication.applications.ApplicationDto
import com.jobapplication.example.jobapplication.applications.ApplicationRepository
import com.jobapplication.example.jobapplication.applications.ApplicationService
import com.jobapplication.example.jobapplication.security.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApplicationServiceImpl(var applicationRepository: ApplicationRepository, var userRepository: UserRepository):ApplicationService {
    override fun getAllApplications(cid:String, jid:String): List<ApplicationDto> {
        return applicationRepository.findAll().filter { it.job?.company?.id == cid && it.job?.id == jid }.map { it.toDTO() }
    }

    override fun createApplication(cid: String,id:String, application: Application) {
        application.id= UUID.randomUUID().toString()
        applicationRepository.save(application)
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
        return "Application Accepted"
    }

    override fun rejectApplication(cid: String, jid: String, aid: String): String {
        val oldApplication = applicationRepository.findById(aid).orElseThrow{RuntimeException("Application Not found")}
        oldApplication.status=false
        applicationRepository.save(oldApplication)
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
