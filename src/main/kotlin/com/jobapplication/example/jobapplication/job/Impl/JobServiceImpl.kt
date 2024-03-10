package com.jobapplication.example.jobapplication.job.Impl

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.job.Job
import com.jobapplication.example.jobapplication.job.JobDto
import com.jobapplication.example.jobapplication.job.JobRepository
import com.jobapplication.example.jobapplication.job.JobService
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobServiceImpl (var jobRepository: JobRepository, var companyRepository: CompanyRepository): JobService {
    override fun findAll(): List<JobDto> {
        return jobRepository.findAll().map { it.toDTO() }
    }

    override fun createJob(id:String, job: Job) {
        println(job)
        job.id= UUID.randomUUID().toString()
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
    }

    override fun getJobById(id: String):Job {
        return jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
    }

    override fun updateJob(id: String, updatedJob: Job) {
        val oldJob = jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
        oldJob.title= updatedJob.title
        oldJob.description= updatedJob.description
        oldJob.minSalary= updatedJob.minSalary
        oldJob.maxSalary= updatedJob.maxSalary
        oldJob.location= updatedJob.location
        jobRepository.save(oldJob)
    }

    override fun deleteJob(id: String) {
        val oldJob = jobRepository.findById(id).orElseThrow{RuntimeException("Job not found")}
        return jobRepository.delete(oldJob)

    }
}