package com.jobapplication.example.jobapplication.job

import com.jobapplication.example.jobapplication.company.Company
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController(val jobService: JobService) {

    @GetMapping("/alljobs")
    fun findAll() : List<JobDto> = jobService.findAll()

    @PostMapping("/jobs/post/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createJob(@PathVariable id: String,@RequestBody job:Job) = jobService.createJob(id,job)

    @GetMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getJobById(@PathVariable id:String)=jobService.getJobById(id)

    @DeleteMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteJob(@PathVariable id:String)=jobService.deleteJob(id)

    @PutMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updateJob(@PathVariable id:String, @RequestBody updatedjob:Job)=jobService.updateJob(id,updatedjob)

}