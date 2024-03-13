package com.jobapplication.example.jobapplication.job.Impl

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.job.Job
import com.jobapplication.example.jobapplication.job.JobDto
import com.jobapplication.example.jobapplication.job.JobRepository
import com.jobapplication.example.jobapplication.job.JobService
import com.jobapplication.example.jobapplication.security.UserEntity
import com.jobapplication.example.jobapplication.security.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobServiceImpl (var jobRepository: JobRepository, var companyRepository: CompanyRepository, var userRepository: UserRepository): JobService {
    override fun findAll(): List<JobDto> {
        return jobRepository.findAll().map { it.toDTOForJob() }
    }

    override fun createJob(id:String, job: Job):ResponseEntity<String> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}

        var companies : List<Company> = companyRepository.findByCompany(id)
        for(it in companies){
            var userid = it.userEntity?.id
            if (userid != completeuser.id){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not the admin of this company")
            }
        }
        println(job)
        job.id= UUID.randomUUID().toString().replace("-","").substring(0,12)
        var company_id = job.company?.id
        println(company_id)
        val companyjob = company_id?.let { companyRepository.findById(it).orElseThrow{RuntimeException("Company not found")} }
        println(companyjob)
        job.company=companyjob
        if (companyjob != null) {
            companyjob.jobs?.addLast(job)
        }
        jobRepository.save(job)

        if (companyjob != null) {
            companyRepository.save(companyjob)
        }

        return ResponseEntity.status(HttpStatus.OK).body("Job created")

    }

    override fun getJobById(id: String):Job {
        return jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
    }

    override fun updateJob(cid: String, id: String, updatedJob: Job):ResponseEntity<String> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}

        var companies : List<Company> = companyRepository.findByCompany(cid)
        for(it in companies){
            var userid = it.userEntity?.id
            if (userid != completeuser.id){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not the admin of this company")
            }
        }
        val oldJob = jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
        oldJob.title= updatedJob.title
        oldJob.description= updatedJob.description
        oldJob.minSalary= updatedJob.minSalary
        oldJob.maxSalary= updatedJob.maxSalary
        oldJob.location= updatedJob.location
        jobRepository.save(oldJob)

        return ResponseEntity.status(HttpStatus.OK).body("Job updated")

    }

    override fun deleteJob(cid: String, id: String): ResponseEntity<String>{
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        println(username)
        var completeuser : UserEntity = userRepository.findByUsername(username).orElseThrow{RuntimeException("User not found")}

        var companies : List<Company> = companyRepository.findByCompany(cid)
        for(it in companies){
            var userid = it.userEntity?.id
            if (userid != completeuser.id){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not the admin of this company")
            }
        }
        val oldJob = jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
        jobRepository.delete(oldJob)
        return ResponseEntity.status(HttpStatus.OK).body("Job deleted")
    }
}