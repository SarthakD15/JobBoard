package com.jobapplication.example.jobapplication.job

import com.jobapplication.example.jobapplication.company.Company

interface JobService {
    fun findAll():List<JobDto>
    fun createJob(id:String, job:Job):String
    fun getJobById(id: String):Job
    fun updateJob(id: String, updatedJob: Job):String
    fun deleteJob(id: String)
}