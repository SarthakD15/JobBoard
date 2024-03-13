package com.jobapplication.example.jobapplication.job

import com.jobapplication.example.jobapplication.company.Company
import org.springframework.http.ResponseEntity

interface JobService {
    fun findAll():List<JobDto>
    fun createJob(id:String, job:Job):ResponseEntity<String>
    fun getJobById(id: String):Job
    fun updateJob(cid: String, id: String, updatedJob: Job):ResponseEntity<String>
    fun deleteJob(cid: String, id: String):ResponseEntity<String>
}